<#include "main-template.ftl"/>

<#macro content>
    <div class="contact-clean">
        <form method="post" id="form3" autocomplete="off">
            <h2 class="text-center">Contact us</h2>
            <div class="form-group"><input class="form-control" type="text" name="name" id="name" placeholder="Name">
            </div>
            <div class="form-group"><input class="form-control is-invalid" type="email" name="email" id="email"
                                           placeholder="Email">
                <small class="form-text text-danger">Please enter a correct email address.</small>
            </div>
            <div class="form-group"><textarea class="form-control" rows="14" name="message" id="message"
                                              placeholder="Message"></textarea></div>
            <div class="form-group">
                <button class="btn btn-primary" type="submit" onclick="return validateForm3()">send</button>
            </div>
        </form>
    </div>
    <script>
        function validateForm3() {
            let name = document.getElementById('name');
            let email_regexp = /[0-9a-zа-я_A-ZА-Я]+@[0-9a-zа-я_A-ZА-Я^.]+\.[a-zа-яА-ЯA-Z]{2,4}/i;
            let email = document.getElementById('email');
            let message = document.getElementById('message');
            let error = '';
            if (name.value.trim() === '') {
                error += 'The name must not be empty! \n';
            }
            if (!email_regexp.test(email.value)) {
                error += 'Email is entered incorrectly! \n';
            }
            if (message.value.length < 20) {
                error += 'The message field must be a minimum of 20 characters! \n';
            }
            if (error !== '') {
                swal("Oops", error, "error");
                return false;
            } else {
                let form = document.getElementById("form3");
                form.submit();
            }
        }
    </script>
    <div class="footer-dark">
        <footer>
            <div class="container">
                <div class="row">
                    <div class="col-sm-6 col-md-3 item">
                        <h3>Services</h3>
                        <ul>
                            <li><a href="#">Web design</a></li>
                            <li><a href="#">Development</a></li>
                            <li><a href="#">Hosting</a></li>
                        </ul>
                    </div>
                    <div class="col-sm-6 col-md-3 item">
                        <h3>About</h3>
                        <ul>
                            <li><a href="#">Company</a></li>
                            <li><a href="#">Team</a></li>
                            <li><a href="#">Careers</a></li>
                        </ul>
                    </div>
                    <div class="col-md-6 item text">
                        <h3>Description</h3>
                        <p>Our team is the best in its field. We do everything to make you happy. We are very glad that
                            you
                            are using our site.</p>
                    </div>
                    <div class="col item social"><a href="#"><i class="icon ion-social-facebook"></i></a><a href="#"><i
                                    class="icon ion-social-twitter"></i></a><a href="#"><i
                                    class="icon ion-social-snapchat"></i></a><a
                                href="#"><i class="icon ion-social-instagram"></i></a></div>
                </div>
                <p class="copyright">BulSchool © 2020</p>
            </div>
        </footer>
    </div>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0/js/bootstrap.bundle.min.js"></script>
</#macro>

<@main name="Feedback" nameCSS="feedback.css"/>



