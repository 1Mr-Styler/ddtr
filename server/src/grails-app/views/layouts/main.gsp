<!doctype html>
<html lang="en" class="no-js">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>
        <g:layoutTitle default="Grails"/>
    </title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <asset:link rel="icon" href="favicon.ico" type="image/x-ico"/>

    <asset:stylesheet src="application.css"/>

    <g:layoutHead/>
</head>

<body>

<!-- A grey horizontal navbar that becomes vertical on small screens -->
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
    <a class="navbar-brand" href="#">DDTR</a>
    <!-- Links -->
    <ul class="navbar-nav">
        <li class="nav-item">
            <a class="nav-link" href="${createLink(controller: "user")}">Operators</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="${createLink(uri: "/DLC")}">DLC</a>
        </li>
        <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">Application</a>

            <div class="dropdown-menu" style="color: #000000">
                <a class="dropdown-item" href="${createLink(controller: "application")}">Application</a>
                <a class="dropdown-item" href="${createLink(controller: "proxy")}">Proxy</a>
            </div>

        </li>
        <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="navbardrop1" data-toggle="dropdown">Cards</a>

            <div class="dropdown-menu" style="color: #000000">
                <a class="dropdown-item" href="${createLink(controller: "temp")}">Temporary Card</a>
                <a class="dropdown-item" href="${createLink(controller: "perm")}">Permanent Card</a>
            </div>

        </li>
        <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="navbardrop2" data-toggle="dropdown">Applicants</a>

            <div class="dropdown-menu" style="color: #000000">
                <a class="dropdown-item" href="${createLink(controller: "applicant")}">Applicants</a>
            </div>

        </li>
        <li class="nav-item">
            <a class="nav-link" href="${createLink(controller: "perm", action: "load")}">Load Cards</a>
        </li>
    </ul>

</nav>

<g:layoutBody/>

<div class="footer row" role="contentinfo">
    <div class="col">
        <a href="http://guides.grails.org" target="_blank">
            <asset:image src="advancedgrails.svg" alt="Grails Guides" class="float-left"/>
        </a>
        <strong class="centered"><a href="http://guides.grails.org" target="_blank">Grails Guides</a></strong>

        <p>Building your first Grails app? Looking to add security, or create a Single-Page-App? Check out the <a
                href="http://guides.grails.org" target="_blank">Grails Guides</a> for step-by-step tutorials.</p>

    </div>

    <div class="col">
        <a href="http://docs.grails.org" target="_blank">
            <asset:image src="documentation.svg" alt="Grails Documentation" class="float-left"/>
        </a>
        <strong class="centered"><a href="http://docs.grails.org" target="_blank">Documentation</a></strong>

        <p>Ready to dig in? You can find in-depth documentation for all the features of Grails in the <a
                href="http://docs.grails.org" target="_blank">User Guide</a>.</p>

    </div>

    <div class="col">
        <a href="https://grails-slack.cfapps.io" target="_blank">
            <asset:image src="slack.svg" alt="Grails Slack" class="float-left"/>
        </a>
        <strong class="centered"><a href="https://grails-slack.cfapps.io" target="_blank">Join the Community</a>
        </strong>

        <p>Get feedback and share your experience with other Grails developers in the community <a
                href="https://grails-slack.cfapps.io" target="_blank">Slack channel</a>.</p>
    </div>
</div>


<div id="spinner" class="spinner" style="display:none;">
    <g:message code="spinner.alt" default="Loading&hellip;"/>
</div>

<asset:javascript src="application.js"/>

</body>
</html>
