# example-java-generics
Example Java project created via STS as Java Project, showing how to;
- generic interface (MyGenericInterface)
- generic class (MyGenericClass)
- generic static methods (ShowMoreStaticGenericMethodsAndBounds, ShowStaticGenericMethodsAndBounds)
- "binding" see ShowMoreStaticGenericMethodsAndBounds, ShowStaticGenericMethodsAndBounds and ShowUpperAndLowerBounds
- "wild char" ? of generic
- "bound type" for interface
- "bound type" with muliple interface
- "bound type" with class
- "lower bound" of variable type
- "upper bound" of variable type
- "lower bound" of method input
- "upper bound" of method input
- "upper bound" makes it; assignable to lists of boundType or its children. And becomes "read only". See ShowUpperAndLowerBounds.java, ShowStaticGenericMethodsAndBounds.java
- "lower bound" makes it; assignable to lists of boundType or its parents. And becomes "write only". Can be read as Object only. See ShowUpperAndLowerBounds.java, ShowStaticGenericMethodsAndBounds.java

## Setup dependencies
Make sure on your machine, you have;
- git installed
- a working unix shell (like git bash. If not there install git and use its "git bash")
- working JDK installed (with JAVA_HOME env variable added, and PATH env variable prefixed with %JAVA_HOME%/bin)

## How to use it  (NOTE in below the directory structure and names are suggested)
### Make sure you created the directory structure you want to store example projects in
As example, at bash command line issue;<br>
mkdir -p /c/it114/exampleProjects

### get(clone) the project from github
cd to where you put your example projects.<br>
As example, at bash command line issue;<br>
cd /c/it114/exampleProjects ) <br>
git clone https://github.com/njit-it114/example-java-generics.git <br>
or<br>
git clone https://github.com/njit-it114/example-java-generics.git  whateverDirectoryNameYouWantStuffToBeClonedInto

### to run
click on this project
Run As --> Java Application (select MainEntry or ShowStaticGenericMethodsAndBounds or ShowMoreStaticGenericMethodsAndBounds or ShowUpperAndLowerBounds from list)

## Project directory structure
- README.md this read me file
- src directory of java packages
- design directory of design documents, like class diagram
 # example-java-generics
