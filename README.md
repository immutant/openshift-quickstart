Immutant on OpenShift
=========================

Here is a quick way to try out your Leiningen-based Clojure
application running in Immutant on OpenShift.

By default, this quickstart will install the latest incremental
version of Immutant. You can specify a different version by tweaking
`.openshift/action_hooks/build`, but any build earlier than
incremental 607 won't work. ;-)

One particularly nice thing about OpenShift is that it provides simple
ssh port forwarding, so you can configure your app to start either a
Swank or nREPL server and connect to it via an ssh tunnel. The only
stipulation is that the port you specify be between 15000-35530. So in
your `project.clj`, you might add:

    :immutant {:nrepl-port 24005}

You can then run `rhc port-forward -a yourapp` to setup your tunnel.
These and other :immutant options can be specified in the deployment
descriptor as well; see `deployments/your-clojure-application.clj`.

Running on OpenShift
--------------------

Create an account at http://openshift.redhat.com/

Create a jbossas-7 application

    rhc app create -a yourapp -t jbossas-7

Remove the sample app provided by the jbossas-7 cartridge

    cd yourapp
    rm -rf pom.xml src

Add this upstream repo

    git remote add upstream -m master git://github.com/immutant/openshift-quickstart.git
    git pull -s recursive -X theirs upstream master

Then add, commit, and push your changes

    git add -A .
    git commit -m "My Changes"
    git push

That's it! The first build will take a minute or two, even after the
push completes, so be patient. You should ssh to your app and run
`tail_all` so you'll have something to watch while your app deploys.

When you see `Deployed "your-clojure-application.clj"` in the log,
point a browser at the following link (adjusted for your namespace)
and you should see a friendly welcome:

    http://yourapp-$namespace.rhcloud.com

Drop in to the `#immutant` IRC channel on freenode.net if you have any
questions.
