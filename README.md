# Handwriting Generator Utility

## Description

This utility GUI desktop program allows to manually generate primitive homogeneous images with certain settings. 
Utility can be used to generate image dataset for training ML models.

## Build & Run

Now you can use this project with cloning this repository:
```bash
git clone https://github.com/MatheMateCS/HandwritingGeneratorUtility
```

Project based on Maven build tool. So, to build and run it you have to have Maven installed.
#### Maven setting up (Windows OS)
- To set up Maven on your machine, you need just download and unpack [this archive](https://dlcdn.apache.org/maven/maven-3/3.9.9/binaries/apache-maven-3.9.9-bin.zip). You can unpack it everywhere you want

- Then add ...\bin folder to PATH (system variables). Your path can be e.g. ```C:\Users\USERNAME\Downloads\apache-maven-3.9.9\bin```

- Also you ought to have JAVA_HOME system variable that leads to JDK. Path can be e.g. ```C:\Program Files\Java\jdk-23```. Take into consideration, that this path doesn't include \bin folder.

Now everything is ready to provide project building. Just launch the run.bash script from cmd: 
```bash
./run.bash
```
And after a several seconds the GUI Desktop Application will appear.

## Examples of using