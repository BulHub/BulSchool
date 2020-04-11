<#include "main-template.ftl"/>

<#macro content>

    <legend></legend>

    <form class="form-horizontal">
        <fieldset>
            <div class="form-group">
                <label class="col-md-4 control-label" for="message">Message:</label>
                <div class="col-md-4">
                    <textarea class="form-control" id="message" name="message"
                              placeholder="(Write your message here)"></textarea>
                </div>
                <div class="form-group">
                    <label class="col-md-4 control-label" for="sendButton"></label>
                    <div class="col-md-4">
                        <button id="sendButton" name="sendButton" class="btn btn-primary">Send</button>
                    </div>
                </div>
        </fieldset>
    </form>

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

</#macro>

<@main name="Quick Support" nameCSS="quickSupport.css"/>

