# Trailblazer

Slideshow viewer app for 3rd year SWEng project

## Usage

The app should be launched via /src/main/java/survivalapp/Main.java

The app expects file paths in the XML file to be relative to that file. 
For example, with a file structure like this:

  slideshow/  
  ├─ slideshow.xml    
  ├─ images/    
  │  ├─ image_1.png    
  │  ├─ image_2.png    
  ├─ audio/  

An image element in slideshow.xml would look like this:  
  \<image urlname="images/image_1.png" xstart="10" ystart="10" width="50" height="60"/>
