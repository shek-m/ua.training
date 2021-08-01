<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8", name="viewport", content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title>Login</title>
</head>
<body>
<div class="container">
    <div class="row">
            <div class="d-flex justify-content-center">
                <div class="card" style="margin-top: 45px">
                    <h4 class="card-header">Login with your username and password</h4>
                    <div class="card-body">
                        <p class="card-text">

                        <#if logout>
                            <div class="alert alert-info" role="alert">You've been logged out successfully.</div>
                        </#if>

                        <#if error>
                            <div class="alert alert-danger" role="alert">Invalid username or password!</div>
                        </#if>

                        <form form th:action="@{/login}" th:method="POST">
                            <input type="hidden" th:name="${_csrf.parameterName}" th:value="${_csrf.token}"/>
                            <div class="mb-3">
                                <label for="username" class="form-label">Username</label>
                                <input type="text" class="form-control" id="username" name="username">
                            </div>
                            <div class="mb-3">
                                <label for="password" class="form-label">Password</label>
                                <input type="text" class="form-control" id="password" name="password">
                            </div>
                            <button type="submit" class="btn btn-primary">Sign in</button>
                        </form>
                        </p>
                    </div>
                </div>
            </div>
    </div>
</div>

</body>
</html>