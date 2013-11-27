JAVA_FILES := $(wildcard *.java)
JAVA_CLASSES := $(addprefix out/,$(JAVA_FILES:%.java=%.class))

out/%.class: %.java
	@mkdir -p $(dir $@)
	javac $< -d $(dir $@)

.PHONY: all
all: $(JAVA_CLASSES)

.PHONY: clean
clean:
	rm -rf out/

serveur: $(JAVA_CLASSES)
	java -classpath out/ Serveur &

client: $(JAVA_CLASSES)
	java -classpath out/ Client

run: $(JAVA_CLASSES)
	java -classpath out/ Serveur &
	java -classpath out/ Client
