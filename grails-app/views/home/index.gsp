<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <title>DocTwin</title>

        <!-- Bootstrap 4.6.1 -->
        <link rel="stylesheet"
              href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">

        <style>
            body {
                background-color: #f8f9fa;
            }
            .card {
                border-radius: 8px;
            }
        </style>

        <script>
            document.addEventListener("DOMContentLoaded", function () {
                const inputs = document.querySelectorAll(".custom-file-input");

                inputs.forEach(input => {
                    input.addEventListener("change", function (e) {
                        const fileName = e.target.files[0]?.name || "Escolher arquivo PDF";
                        e.target.nextElementSibling.innerText = fileName;
                    });
                });
            });
        </script>
    </head>
    <body>
        <nav class="navbar navbar-dark bg-primary">
            <div class="container justify-content-center">
                <span class="navbar-brand mb-0 h1 font-weight-bold">
                    DocTwin
                </span>
            </div>
        </nav>

        <div id="errorContainer" class="row justify-content-center mt-5" ${flash.error ? "" : "hidden"}>
            <div class="col-md-8 col-lg-5">
                <div id="errorMessage" class="alert alert-danger text-center">
                    ${flash.error}
                </div>
            </div>
        </div>
        <div class="container mt-5">
            <div class="row justify-content-center">
                <div class="col-md-10 col-lg-6">.36,

                    <div class="card shadow-sm">
                        <div class="card-body">

                            <h3 class="card-title text-center mb-4">
                                Similaridade de Documentos
                            </h3>

                            <p class="text-center text-muted mb-4">
                                Carregue dois documentos de texto para analisar a similaridade entre eles
                            </p>

                            <g:form onsubmit="return validateFiles()"
                                    controller="similarity"
                                    action="compare"
                                    method="POST"
                                    enctype="multipart/form-data">

                                <div class="custom-file">
                                    <input type="file"
                                        class="custom-file-input"
                                        id="file1"
                                        name="file1"
                                        accept="application/pdf"
                                        required>

                                    <label class="custom-file-label" for="file1">
                                        Escolher arquivo PDF
                                    </label>
                                </div>

                                <div class="custom-file mt-3">
                                    <input type="file"
                                        class="custom-file-input"
                                        id="file2"
                                        name="file2"
                                        accept="application/pdf"
                                        required>

                                    <label class="custom-file-label" for="file2">
                                        Escolher arquivo PDF
                                    </label>
                                </div>

                                <button type="submit" class="btn btn-primary btn-block mt-4">
                                    Analisar Similaridade
                                </button>
                            </g:form>
                        </div>
                    </div>

                    <footer class="text-center mt-3 text-muted">
                        <small>Uninter · DocTwin · Similaridade</small>
                    </footer>
                </div>
            </div>
        </div>

        <asset:javascript src="file-validator.js"/>

        <!-- Bootstrap JS -->
        <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>