<#include "main-template.ftl"/>

<#macro content>
<head>
    <title>Academics &mdash; Website by BulSchool</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">


    <link href="https://fonts.googleapis.com/css?family=Muli:300,400,700,900" rel="stylesheet">
    <link rel="stylesheet" href="${rc.getContextPath()}/templates/fonts/icomoon/style.css">

    <link rel="stylesheet" href="${rc.getContextPath()}/templates/css/bootstrap.min.css">
    <link rel="stylesheet" href="${rc.getContextPath()}/templates/css/jquery-ui.css">
    <link rel="stylesheet" href="${rc.getContextPath()}/templates/css/owl.carousel.min.css">
    <link rel="stylesheet" href="${rc.getContextPath()}/templates/css/owl.theme.default.min.css">
    <link rel="stylesheet" href="${rc.getContextPath()}/templates/css/owl.theme.default.min.css">

    <link rel="stylesheet" href="${rc.getContextPath()}/templates/css/jquery.fancybox.min.css">

    <link rel="stylesheet" href="${rc.getContextPath()}/templates/css/bootstrap-datepicker.css">

    <link rel="stylesheet" href="${rc.getContextPath()}/templates/fonts/flaticon/font/flaticon.css">

    <link rel="stylesheet" href="${rc.getContextPath()}/templates/css/aos.css">
    <link href="${rc.getContextPath()}/templates/css/jquery.mb.YTPlayer.min.css" media="all" rel="stylesheet"
          type="text/css">

    <link rel="stylesheet" href="${rc.getContextPath()}/templates/css/style.css">


</head>

<body data-spy="scroll" data-target=".site-navbar-target" data-offset="300">

<div class="site-wrap">

    <div class="site-mobile-menu site-navbar-target">
        <div class="site-mobile-menu-header">
            <div class="site-mobile-menu-close mt-3">
                <span class="icon-close2 js-menu-toggle"></span>
            </div>
        </div>
        <div class="site-mobile-menu-body"></div>
    </div>


    <div class="py-2 bg-light">
    </div>

    <div class="site-section ftco-subscribe-1 site-blocks-cover pb-4"
         style="background-image: url('.//templates/images/bg_1.jpg')">
        <div class="container">
            <div class="row align-items-end">
                <div class="col-lg-7">
                    <h2 class="mb-0">Contact</h2>
                    <p>Lorem ipsum dolor sit amet consectetur adipisicing.</p>
                </div>
            </div>
        </div>
    </div>


    <div class="custom-breadcrumns border-bottom">
        <div class="container">
            <a href="${rc.getContextPath()}/home">Home</a>
            <span class="mx-3 icon-keyboard_arrow_right"></span>
            <span class="current">Contact</span>
        </div>
    </div>

    <form id="form3" autocomplete="off" method="post">
        <div class="site-section">
            <div class="container">
                <div class="row">
                    <div class="col-md-6 form-group">
                        <label for="firstName">First Name</label>
                        <input type="text" id="firstName" name="firstName" class="form-control form-control-lg">
                    </div>
                    <div class="col-md-6 form-group">
                        <label for="lastName">Last Name</label>
                        <input type="text" id="lastName" name="lastName" class="form-control form-control-lg">
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6 form-group">
                        <label for="email">Email Address</label>
                        <input type="text" id="email" name="email" class="form-control form-control-lg">
                    </div>
                    <div class="col-md-6 form-group">
                        <label for="telephone">Tel. Number</label>
                        <input type="text" id="telephone" name="telephone" class="form-control form-control-lg">
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12 form-group">
                        <label for="message">Message</label>
                        <textarea name="message" id="message" cols="30" rows="10" class="form-control"></textarea>
                    </div>
                </div>

                <div class="row">
                    <div class="col-12">
                        <input type="submit" onclick="return validateForm3()" value="Send Message"
                               class="btn btn-primary btn-lg px-5">
                    </div>
                </div>
            </div>
        </div>
    </form>


    <script>
        function validateForm3() {
            let name1 = document.getElementById('firstName');
            let name2 = document.getElementById('lastName');
            let email_regexp = /[0-9a-zа-я_A-ZА-Я]+@[0-9a-zа-я_A-ZА-Я^.]+\.[a-zа-яА-ЯA-Z]{2,4}/i;
            let email = document.getElementById('email');
            let message = document.getElementById('message');
            let error = '';
            if (name1.value.trim() === '') {
                error += 'The first_name must not be empty! \n';
            }
            if (name2.value.trim() === '') {
                error += 'The last_name must not be empty! \n';
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

    <div class="section-bg style-1" style="background-image: url('.//templates/images/hero_1.jpg');">
        <div class="container">
            <div class="row">
                <div class="col-lg-4 col-md-6 mb-5 mb-lg-0">
                    <h3>Our Philosphy</h3>
                    <p>Lorem ipsum dolor sit, amet consectetur adipisicing elit. Reiciendis recusandae, iure repellat
                        quis delectus ea? Dolore, amet reprehenderit.</p>
                </div>
                <div class="col-lg-4 col-md-6 mb-5 mb-lg-0">
                    <h3>Academics Principle</h3>
                    <p>Lorem ipsum dolor sit, amet consectetur adipisicing elit. Reiciendis recusandae, iure repellat
                        quis delectus ea?
                        Dolore, amet reprehenderit.</p>
                </div>
                <div class="col-lg-4 col-md-6 mb-5 mb-lg-0">
                    <h3>Key of Success</h3>
                    <p>Lorem ipsum dolor sit, amet consectetur adipisicing elit. Reiciendis recusandae, iure repellat
                        quis delectus ea?
                        Dolore, amet reprehenderit.</p>
                </div>
            </div>
        </div>
    </div>

</div>

<div id="loader" class="show fullscreen">
    <svg class="circular" width="48px" height="48px">
        <circle class="path-bg" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke="#eeeeee"/>
        <circle class="path" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke-miterlimit="10"
                stroke="#51be78"/>
    </svg>
</div>

<script src="${rc.getContextPath()}/templates/js/jquery-3.3.1.min.js"></script>
<script src="${rc.getContextPath()}/templates/js/jquery-migrate-3.0.1.min.js"></script>
<script src="${rc.getContextPath()}/templates/js/jquery-ui.js"></script>
<script src="${rc.getContextPath()}/templates/js/popper.min.js"></script>
<script src="${rc.getContextPath()}/templates/js/bootstrap.min.js"></script>
<script src="${rc.getContextPath()}/templates/js/owl.carousel.min.js"></script>
<script src="${rc.getContextPath()}/templates/js/jquery.stellar.min.js"></script>
<script src="${rc.getContextPath()}/templates/js/jquery.countdown.min.js"></script>
<script src="${rc.getContextPath()}/templates/js/bootstrap-datepicker.min.js"></script>
<script src="${rc.getContextPath()}/templates/js/jquery.easing.1.3.js"></script>
<script src="${rc.getContextPath()}/templates/js/aos.js"></script>
<script src="${rc.getContextPath()}/templates/js/jquery.fancybox.min.js"></script>
<script src="${rc.getContextPath()}/templates/js/jquery.sticky.js"></script>
<script src="${rc.getContextPath()}/templates/js/jquery.mb.YTPlayer.min.js"></script>


<script src="${rc.getContextPath()}/templates/js/main.js"></script>

</#macro>

<@main name="Contact" nameCSS="developers.css"/>
