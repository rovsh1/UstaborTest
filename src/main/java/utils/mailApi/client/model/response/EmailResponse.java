package utils.mailApi.client.model.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties( ignoreUnknown = true )
public class EmailResponse {

    private final Long id;
    private final String from;
    private final String recipient;
    private final String subject;
    private final String excerpt;
    private final String body;
    private final String timestamp;
    private final Boolean read;

    @JsonCreator
    public EmailResponse(
            @JsonProperty( "mail_id" ) Long id,
            @JsonProperty( "mail_from" ) String from,
            @JsonProperty( "mail_recipient" ) String recipient,
            @JsonProperty( "mail_subject" ) String subject,
            @JsonProperty( "mail_excerpt" ) String excerpt,
            @JsonProperty( "mail_body" ) String body,
            @JsonProperty( "mail_timestamp" ) String timestamp,
            @JsonProperty( "mail_read" ) Integer read ) {

        this.id = id;
        this.from = from;
        this.recipient = recipient;
        this.subject = subject;
        this.excerpt = excerpt;
        this.body = body;
        this.timestamp = timestamp;
        this.read = read == 1;
    }

    public Long getId() {
        return id;
    }

    public String getFrom() {
        return from;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getSubject() {
        return subject;
    }

    public String getExcerpt() {
        return excerpt;
    }

    public String getBody() {
        return body;
    }

    public String getTimestamp() {
        return timestamp;
    }


    public Boolean getRead() {
        return read;
    }

    @Override
    public String toString() {
        return "EmailResponse{" +
                "id=" + id +
                ", from='" + from + '\'' +
                ", recipient='" + recipient + '\'' +
                ", subject='" + subject + '\'' +
                ", excerpt='" + excerpt + '\'' +
                ", body='" + body + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", read=" + read +
                '}';
    }
}
