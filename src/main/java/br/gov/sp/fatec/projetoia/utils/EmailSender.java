package br.gov.sp.fatec.projetoia.utils;

import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Service;

@Service
public class EmailSender {

    private void enviarEmail(String email, String templateId, String params) throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("https://api.emailjs.com/api/v1.0/email/send");

        String json = String.format("{\"service_id\":\"%s\",\"template_id\":\"%s\",\"user_id\":\"%s\",\"template_params\":%s}",
                                    "service_wgroxqa", templateId, "77S0Lzg7EDjYoyApF", params);

        StringEntity entity = new StringEntity(json);
        httpPost.setEntity(entity);
        httpPost.setHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.getMimeType());

        httpClient.execute(httpPost);
        httpClient.close();
    }

    public void sendNewUserEmail(String email, String password) throws Exception {
        String params = "{\"password\":\"" + password + "\"}";
        enviarEmail(email, "template_976z1bk", params);
    }
}
