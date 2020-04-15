<#include "main-template.ftl"/>

<#macro content>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Document</title>
        <script
                src="https://code.jquery.com/jquery-3.4.1.min.js"
                integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
                crossorigin="anonymous"></script>
        <script src="${rc.getContextPath()}/js/chat.js"></script>
    </head>
    <body onload="sendMessage('${email}', 'Login')">
    <div class="form-group">
        <label class="col-md-4 control-label" for="message">Message:</label>
        <div class="col-md-4">
                    <textarea class="form-control" id="message" name="message"
                              placeholder="(Write your message here)"></textarea>
        </div>
        <div class="form-group">
            <label class="col-md-4 control-label" for="sendButton"></label>
            <div class="col-md-4">
                <button class="btn btn-primary"
                        onclick="sendMessage('${email}',
                                $('#message').val())">Send
                </button>
            </div>
        </div>

        <div>
            <ul id="messages">

            </ul>
        </div>


        <div class="my">
            <img src="${rc.getContextPath()}/img/bandmember.jpg" alt="Avatar">
            <p>Hello. How are you today?</p>
            <span class="time-right">11:00</span>
        </div>

        <div class="my dar">
            <img src="${rc.getContextPath()}/img/avatar_g2.jpg" alt="Avatar" class="right">
            <p>Hey! I'm fine. Thanks for asking!</p>
            <span class="time-left">11:01</span>
        </div>

        <div class="my">
            <img src="${rc.getContextPath()}/img/bandmember.jpg" alt="Avatar">
            <p>Sweet! So, what do you wanna do today?</p>
            <span class="time-right">11:02</span>
        </div>

        <div class="my dar">
            <img src="${rc.getContextPath()}/img/avatar_g2.jpg" alt="Avatar" class="right">
            <p>Nah, I dunno. Play soccer.. or learn more coding perhaps?</p>
            <span class="time-left">11:05</span>
        </div>
    </body>
</#macro>

<@main name="Chat" nameCSS="chat.css"/>

