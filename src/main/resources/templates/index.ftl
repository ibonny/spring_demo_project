<html>
    <p>This is a test.</p>

    <p>Your name is: ${name}</p>

    <p id="testParam"></p>

    <#--  This is a good way to take a variable from Spring Boot and inject it
          into Javascript.  -->
    <p style="display: none;" id="nameval">${name}</p>

    <script src='/webjars/jquery/3.4.1/jquery.js'></script>
    <script src="js/index.js"></script>
</html>
