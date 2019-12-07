# File Compression

A library of basic file compression methods, utilizing both Huffman and LZW encoding. Originally created for CS 375.


## Features

Compress files through Huffman encoding. For example, file.txt -> file.txt.hh

Decompress Huffman encoded files. For example, file.txt.hh -> file.txt

Compress files through LZW encoding. For example, file.txt -> file.txt.ll

Decompress LZW encoded files. For example, file.txt.ll -> file.txt

Create LZW-compressed archives of multiple files. For example, file1.txt + file2.txt -> files.zl

Extract and decompress archives of multiple files. For example, files.zl -> file1.txt + file2.txt

## Design

With Huffman coding, bit sequences are assigned to each character so that a bit sequence assigned to any given character isn't a prefix of code for any other character's bit sequence. This way it's clear when each character starts and stops, at the cost of storage. Huffman is especially slow because it needs to read through the file it's compressing twice: first to build a model based on the file, then to actually encode the file.

LZW compression uses a dictionary system to assign bit sequences to characters and series of characters to ensure duplicate data can be minimally represented. Unlike Huffman, it only has to parse through a file once as it encodes the data it reads. This should result in a more efficient runtime. This also means it will be less efficient for files with little to no repetition.

## Testing

There are a total of 12 unit tests, split evenly between the 3 major features 

**LZW Compression/Decompression Tests**
- Compress a single file
- Compress multiple files at once
- Decompress a single file
- Decompress multiple files at once

**Huffman Compression/Decompression Tests**
- Compress a single file
- Compress multiple files at once
- Decompress a single file
- Decompress multiple files at once

**LZW Archive Compression/Decompression Tests**
- Compress and archive a single file
- Compress multiple files at once and archive them
- Decompress and extract a single file archive
- Decompress and extract multiple files from an archive with multiple files

## Installation

To run any of the methods, use the following commands to compile them:

```
javac Deschubs.java
javac DeschubsArc.java
javac SchubsArc.java
javac SchubsH.java
javac SchubsL.java
```

## Test Instructions

To run the maven tests, use ```mvn test```

## Run examples

- Compress a single text file using Huffman: ```java SchubsH filename.txt```
- Compress multiple text files using Huffman: ```java SchubsH file1.txt file2.txt```
- Compress all text files in a given directory using Huffman: ```java SchubsH *.txt```


* Compress a single text file using LZW: ```java SchubsL filename.txt```
* Compress multiple text files using LZW: ```java SchubsL file1.txt file2.txt```
* Compress all text files in a given directory using LZW: ```java SchubsL *.txt```


- Decompress a single compressed file: ```java Deschubs filename.txt.ll```
- Decompress multiple compressed files: ```java Deschubs file1.txt.ll file2.txt.hh```
- Decompress all LZW-compressed files in a given directory: ```java Deschubs *.ll```


* Compress and archive a single text file using LZW: ```java SchubsArc archivename.zl filename.txt```
* Compress multiple text files using LZW: ```java SchubsArc file1.txt file2.txt```
* Compress all text files in a given directory using LZW: ```java SchubsArc *.txt```


- Decompress a single compressed archive: ```java Deschubs archivename.zl```
- Decompress multiple compressed archive: ```java Deschubs archive1.zl archive2.zl```
- Decompress all LZW-compressed archives in a given directory: ```java Deschubs *.zl```
