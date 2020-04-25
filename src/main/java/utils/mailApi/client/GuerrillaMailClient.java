package utils.mailApi.client;


import utils.mailApi.client.model.request.AddressRequest;
import utils.mailApi.client.model.request.EmailRequest;
import utils.mailApi.client.model.request.EmailsRequest;
import utils.mailApi.client.model.response.AddressResponse;
import utils.mailApi.client.model.response.EmailResponse;
import utils.mailApi.client.model.response.EmailsResponse;

public interface GuerrillaMailClient {

    /**
     *
     * @param addressRequest cannot be null
     * @return
     */
    AddressResponse address(AddressRequest addressRequest );

    /**
     *
     * @param emailRequest cannot be null
     * @return
     */
    EmailResponse email(EmailRequest emailRequest );

    /**
     *
     * @param emailsRequest cannot be null
     * @return
     */
    EmailsResponse emails(EmailsRequest emailsRequest );
}
