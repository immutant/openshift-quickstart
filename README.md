Immutant on OpenShift
=========================

This is a (hopefully) short-term solution for trying out your
Leiningen-based Clojure applications running in Immutant on OpenShift.
Longer term, we hope to create an Immutant cartridge that you can
embed into your AS7/EAP6 OpenShift applications.

By default, this quickstart will install the latest incremental
version of Immutant. You can specify a different version by tweaking
`.openshift/action_hooks/build`.

One particularly nice thing about OpenShift is that it provides simple
ssh port forwarding, so you can configure your app to start either a
Swank or nREPL server and connect to it via an ssh tunnel. The only
stipulation is that the port you specify be between 15000-35530. So in
your `project.clj`, you can add:

    :immutant {:nrepl-port 24005}

You can then run `rhc port-forward -a yourapp` to setup your tunnel.

Running on OpenShift
--------------------

Create an account at http://openshift.redhat.com/

Create a jbossas-7 application

    rhc app create -a yourapp -t jbossas-7

Add this upstream repo

    cd yourapp
    git remote add upstream -m master git://github.com/immutant/openshift-quickstart.git
    git pull -s recursive -X theirs upstream master

Remove the sample app provided by the jbossas-7 cartridge

    rm -rf pom.xml src

Create, copy or pull a Leiningen app in

    lein new tmp
    cp -R tmp/* .
    rm -rf tmp
    
Then push the repo upstream

    git push

That's it, you can now checkout your application at:

    http://yourapp-$namespace.rhcloud.com

