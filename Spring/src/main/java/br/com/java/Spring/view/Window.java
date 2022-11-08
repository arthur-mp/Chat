package br.com.java.Spring.view;

import br.com.java.Spring.model.Message;
import org.springframework.context.annotation.Scope;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;

@Component
@Scope("singleton")
public class Window  implements ActionListener {

    JFrame jFrame = new JFrame();

    private String text = "";

    JLabel title = new JLabel("Chat Spring");

    JTextArea fieldChat = new JTextArea("");

    JTextField input = new JTextField();

    JButton send = new JButton("Send");

    public Window(){
        setConfigsWindow();

        jFrame.add(title);
        title.setBounds(250, 20, 100, 30);

        jFrame.add(fieldChat);
        fieldChat.setBounds(20, 50, 560, 350);
        fieldChat.setEditable(false);

        jFrame.add(input);
        input.setBounds(20, 410, 460, 40);

        jFrame.add(send);
        send.setBounds(485, 410, 95, 40);
        send.addActionListener(this);
    }

    private void setConfigsWindow(){
        jFrame.setTitle("Aplicação Java");
        jFrame.setLayout(null);
        jFrame.setSize(600,500);
        jFrame.setVisible(true);
        jFrame.setResizable(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
    }


    public void createMessage(Message message){
        text += "\n"+message.getName()+": "+message.getMessage();
        fieldChat.setText(text);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Message message = new Message();
        message.setName("Spring");
        message.setMessage(input.getText());


        // URL Service Node
        String url = "http://localhost:3000/chat_node";

        try{
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

            HttpEntity<Message> entity = new HttpEntity<>(message, headers);

            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<Message> response = restTemplate.postForEntity(url, entity, Message.class);

            if(response.getStatusCode().equals(HttpStatus.OK)){
                createMessage(message);
                input.setText("");
            }
        }catch (Exception error){
            error.printStackTrace();
        }


    }
}
