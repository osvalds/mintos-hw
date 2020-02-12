```
$$\      $$\ $$\            $$\                         
$$$\    $$$ |\__|           $$ |                        
$$$$\  $$$$ |$$\ $$$$$$$\ $$$$$$\    $$$$$$\   $$$$$$$\ 
$$\$$\$$ $$ |$$ |$$  __$$\\_$$  _|  $$  __$$\ $$  _____|
$$ \$$$  $$ |$$ |$$ |  $$ | $$ |    $$ /  $$ |\$$$$$$\  
$$ |\$  /$$ |$$ |$$ |  $$ | $$ |$$\ $$ |  $$ | \____$$\ 
$$ | \_/ $$ |$$ |$$ |  $$ | \$$$$  |\$$$$$$  |$$$$$$$  |
\__|     \__|\__|\__|  \__|  \____/  \______/ \_______/ 
```

# Mintos frontend developer homework assignment

This is my solution for the Mintos frontend developer homework assignment. 
It was bootstrapped using the [Reagent's Leinigen template](https://github.com/reagent-project/reagent-template). 
The project's structure might seem confusing to developers who don't have experience with clj/cljs projects but after a
short while with the repo it should be okay.  The parts that will be interesting for you is [here](src/cljs/mintos_hw/core.cljs)
and [here](resources/public/css/site.css).

The solution is also hosted on [Heroku](https://rocky-chamber-01633.herokuapp.com/). One of the drawbacks of hosting on Heroku
is that Heroku doesn't let you enable gzip on server level, the application itself must support it. However, the server
that is used in this template doesn't let you use gzip for resource files. This means that the shipped js bundle is 
hilariously large. The reason is that the bundle must include ClojureScript's runtime, plus React and other smaller 
dependencies.

## Running this thing locally

To run the solution locally you will need [Leinigen](https://leiningen.org/) (assuming you have Node & Java JDK 11 already). The easiest way to install it is via `brew`:

```
brew install leinigen
``` 

To start the Figwheel compiler and running the project locally, navigate to the project folder and run the following command in the terminal:

```
lein figwheel
```

Figwheel will automatically push cljs changes to the browser. The server will be available at [http://0.0.0.0:3449](http://0.0.0.0:3449) once Figwheel starts up. 
So if you want to play around with the solution, just change something in the  [core](src/cljs/mintos_hw/core.cljs) file
and those changes will be hot-reloaded in the browser. One thing to note is that it will reset the app's "state" because
it is stored in Reagent's `atom`. Another suggestion is to disable cache in the network tab to avoid weird behaviour. 
