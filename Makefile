prod:
	clojure -A:prod:prod/build:build:build/once
	lib/bin/onejar -A:prod:prod/build:build --args '-m edge.main' project.jar
