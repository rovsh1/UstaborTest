package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.*;
import java.util.concurrent.TimeoutException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;

/**
     * Utility for interacting with an Email application
     */
    public class EmailUtilities {

        final static Logger logger = LoggerFactory.getLogger(EmailUtilities.class);

        private Folder folder;

        public enum EmailFolder {
            INBOX("INBOX"),
            SPAM("SPAM");

            private String text;

            EmailFolder(String text){
                this.text = text;
            }

            public String getText() {
                return text;
            }
        }

        /**
         * Uses username and password in properties file to read from a given folder of the email application
         * @param login Email username (e.g. janedoe@email.com)
         * @param password Email password
         * @throws MessagingException
         */
        public EmailUtilities(String login, String password) throws MessagingException {
            this(
                    login,
                    password,
                    Config.getEmail().getHost(),
                    EmailFolder.INBOX
            );
        }

        /**
         * Connects to email server with credentials provided to read from a given folder of the email application
         * @param username Email username (e.g. janedoe@email.com)
         * @param password Email password
         * @param server Email server (e.g. smtp.email.com)
         * @param emailFolder Folder in email application to interact with
         */
        public EmailUtilities(String username, String password, String server, EmailFolder emailFolder)
                throws MessagingException {
            Properties props = System.getProperties();
            try {
                props.load(new FileInputStream(new File("email.properties")));
            } catch(Exception e) {
                e.printStackTrace();
                System.exit(-1);
            }

            Session session = Session.getInstance(props);
            Store store = session.getStore("imaps");
            store.connect(server, username, password);

            folder = store.getFolder(emailFolder.getText());
            folder.open(Folder.READ_WRITE);
        }


        //************* EMAIL ACTIONS *******************

        public void openEmail(Message message) throws Exception{
            message.getContent();
        }

        public void markAllAsReadAndDeleteEmails() throws Exception {
            logger.info("Mark as read and delete");
            Message[] messages = getAllMessages();
            Arrays.stream(messages).forEach(message -> {
                try {
                    message.setFlag(Flags.Flag.SEEN, true);
                    message.setFlag(Flags.Flag.DELETED, true);
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            });
            folder.close(true);
        }

        public int getNumberOfMessages() throws MessagingException {
            return folder.getMessageCount();
        }

        public int getNumberOfUnreadMessages()throws MessagingException {
            return folder.getUnreadMessageCount();
        }

        /**
         * Gets a message by its position in the folder. The earliest message is indexed at 1.
         */
        public Message getMessageByIndex(int index) throws MessagingException {
            return folder.getMessage(index);
        }

        public Message getLatestMessage() throws MessagingException {
            logger.info("Getting latest message");
            if (getNumberOfMessages() == 0) { return null; }
            return getMessageByIndex(getNumberOfMessages());
        }

        /**
         * Gets all messages within the folder
         */
        public Message[] getAllMessages() throws MessagingException {
            return folder.getMessages();
        }

        /**
         * Returns HTML of the email's content
         */
        public String getMessageContent(Message message) {
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new InputStreamReader(message.getInputStream()));
            } catch (IOException | MessagingException e) {
                e.printStackTrace();
            }
            assert reader != null;
            return reader.lines().collect(Collectors.joining());
        }

        /**
         * Returns all urls from an email message with the linkText specified
         */
        public List<String> getUrlsFromMessage(Message message, String linkText) throws Exception{
            String html = getMessageContent(message);
            List<String> allMatches = new ArrayList<>();
            Matcher matcher = Pattern.compile("(<a [^>]+>)"+linkText+"</a>").matcher(html);
            while (matcher.find()) {
                String aTag = matcher.group(1);
                allMatches.add(aTag.substring(aTag.indexOf("utils/http"), aTag.indexOf("\">")));
            }
            return allMatches;
        }

        /**
         * Gets text from the end of a line.
         * In this example, the subject of the email is 'Authorization Code'
         * And the line to get the text from begins with 'Authorization code:'
         * Change these items to whatever you need for your email. This is only an example.
         */
        public String getAuthorizationCode() throws Exception {
            Message email = waitForMessageByContentText(XmlParser.getTextByKey("AuthCode"));

            BufferedReader reader = new BufferedReader(new InputStreamReader(email.getInputStream()));

            String line;

            while ((line = reader.readLine()) != null) {
                if(line.startsWith(Objects.requireNonNull(XmlParser.getTextByKey("AuthCode")))) {

                    var code =  line.substring(line.indexOf(":") + 1, line.indexOf(":") + 7);
                    logger.info("Code found, return code: " + code);

                    return code;
                }
            }
            return null;
        }

        public String getResetPasswordUrl() throws Exception {
            Message email = waitForMessageByContentText("/auth/password/");

            BufferedReader reader = new BufferedReader(new InputStreamReader(email.getInputStream()));

            String line;
            String prefix = "/auth/password/";

            while ((line = reader.readLine()) != null) {
                if(line.contains(prefix)) {
                    return line.substring(line.indexOf("\"") + 1, line.lastIndexOf("\""));
                }
            }
            return null;
        }

        public Message waitForMessageByContentText(String text) throws MessagingException, TimeoutException {
            folder.open(Folder.READ_WRITE);
            logger.info("Folder opened");

            WaitHelper.pollingWait(5 * 60000, 2000, () -> {
                try {
                    Message email = getLatestMessage();
                    if (email == null) { return false; }
                    logger.info("Looking for text in message: " + text);
                    return isTextInMessage(email, text);
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
                return false;
            });
            logger.info("Return message");
            return getLatestMessage();
        }

        //************* BOOLEAN METHODS *******************

        /**
         * Searches an email message for a specific string
         */
        public boolean isTextInMessage(Message message, String text) {
            String content = getMessageContent(message);

            //Some Strings within the email have whitespace and some have break coding. Need to be the same.
            content = content.replace("&nbsp;", " ");
            return content.contains(text);
        }

        public boolean isMessageUnread(Message message) throws Exception {
            return !message.isSet(Flags.Flag.SEEN);
        }
        //

        private void waitForEmailIsNotEmpty() throws TimeoutException {
            WaitHelper.pollingWait(240000, 2000, () -> {
                try {
                    return getNumberOfMessages() > 0;
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
                return false;
            });
        }
    }
