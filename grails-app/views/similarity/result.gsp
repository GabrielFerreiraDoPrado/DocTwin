<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <title>Similarity Result</title>

        <link rel="stylesheet"
            href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
    </head>
    <body>
        <nav class="navbar navbar-dark bg-primary">
            <div class="container justify-content-center">
                <span class="navbar-brand mb-0 h1 font-weight-bold">
                    DocTwin
                </span>
            </div>
        </nav>

        <div class="container mt-5">
            <div class="row justify-content-center">
                <div class="col-md-8">
                    <div class="card shadow-sm">
                        <div class="card-body">
                            <h3 class="card-title mb-4">Resultado da Similaridade</h3>

                            <p>
                                <strong>Documento 1:</strong>
                                ${document1}
                            </p>

                            <p>
                                <strong>Documento 2:</strong>
                                ${document2}
                            </p>

                            <hr>

                            <h4 class="text-center">
                                Índice de Similaridade
                            </h4>

                            <h1 class="text-center text-primary">
                                ${(similarityScore * 100).round(2)}%
                            </h1>

                            <div class="text-center mt-4">
                                <a href="${createLink(controller:'home', action:'index')}"
                                class="btn btn-secondary">
                                    Comparar outros documentos
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
