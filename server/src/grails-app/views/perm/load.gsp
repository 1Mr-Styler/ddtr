<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'perm.label', default: 'Perm')}"/>
    <title><g:message code="default.create.label" args="[entityName]"/></title>
</head>

<body>
<g:uploadForm action="load">
    <input type="file" name="csv">
    <g:submitButton name="upload" value="Upload"/>
</g:uploadForm>

</body>
</html>
