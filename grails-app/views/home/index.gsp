<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <title>Document Similarity Analyzer</title>

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
    </head>
    <body>
        <div class="container mt-5">
            <div class="row justify-content-center">
                <div class="col-md-8 col-lg-6">

                    <div class="card shadow-sm">
                        <div class="card-body">

                            <h3 class="card-title text-center mb-4">
                                Analizador de Similaridade de Documentos
                            </h3>

                            <p class="text-center text-muted mb-4">
                                Carregue dois documentos de texto para analisar a similaridade entre eles
                            </p>

                            <g:form controller="similarity"
                                    action="compare"
                                    method="POST"
                                    enctype="multipart/form-data">

                                <div class="form-group">
                                    <label for="file1">Primeiro Documento</label>
                                    <input type="file"
                                           class="form-control-file"
                                           id="file1"
                                           name="file1"
                                           required>
                                </div>

                                <div class="form-group">
                                    <label for="file2">Segundo Documento</label>
                                    <input type="file"
                                           class="form-control-file"
                                           id="file2"
                                           name="file2"
                                           required>
                                </div>

                                <button type="submit" class="btn btn-primary btn-block">
                                    Analisar Similaridade
                                </button>
                            </g:form>
                        </div>
                    </div>

                    <footer class="text-center mt-3 text-muted">
                        <small>Grails 5 · Bootstrap 4 · Similarity</small>
                    </footer>

                </div>
            </div>
        </div>

        <!-- Bootstrap JS -->
        <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>