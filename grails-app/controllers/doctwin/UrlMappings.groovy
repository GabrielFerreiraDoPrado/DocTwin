package doctwin

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(controller: "home", action: "index")
        "500"(controller: "error", action: "index")
        "404"(controller: "home", action: "index")
    }
}
