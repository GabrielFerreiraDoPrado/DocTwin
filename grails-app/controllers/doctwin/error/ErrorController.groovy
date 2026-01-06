package doctwin.error

import org.springframework.web.multipart.MaxUploadSizeExceededException

class ErrorController {

    def index() {
        Exception exception = request.exception

        flash.error = "Erro inesperado ao processar a requisição."
        
        redirect(controller: "home", action: "index")
    }
}
