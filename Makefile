default: compile

clean:
	./gradlew clean

compile:
	./gradlew build -xtest

build: compile

jar:
	./gradlew serverJar

versioncheck:
	./gradlew dependencyUpdates

logs:
	heroku logs --tail