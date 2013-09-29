class UrlMappings {
    static mappings = {
        def keyRegex = /[a-z0-9]{10}/
        name huntKey: "/hunt/$key"{
            controller = 'hunt'
            action = 'show'
            constraints {
                key(matches:keyRegex) //key is ten characters long [a-z0-9]{10,10}
            }
        }

//        name indexSignup: "/index/signup"{
//            
//        }

        //needs to exclude existing paths or else causes redirect loop, maybe move this to root for even cleaner urls
        name user: "/$login"{
            controller = 'user'
            action = 'show'
            constraints {
                login(validator: {!['hunt', 'photo', 'prompt', 'user', 'authentication'].contains(it)}) 
            }
        }
    
        name prompt: "/hunt/$key/$id?" {
            controller = 'prompt'
            action = 'show'
            constraints {
                key(matches:keyRegex) //key is ten characters long [a-z0-9]{10,10}
            }
        }
        
        name editPrompt: "/hunt/$key/$action?/$id?" {
            controller = 'prompt'
            action = 'edit'
            constraints {
                key(matches:keyRegex) //key is ten characters long [a-z0-9]{10,10}
            }
        }

        "/$controller/$action?/$id?"{
            constraints {
                // apply constraints here
            }
        }

        '/'{
            controller = 'index'
            action = 'index'
        }



        '500'(view:'/error')
    }
}