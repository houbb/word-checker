# Project Description

[中文文档](README_EN.md)

This item is used for word spell checking.

Support English word spelling detection, and Chinese spelling detection.

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.houbb/word-checker/badge.svg)](http://mvnrepository.com/artifact/com.github.houbb/word-checker)
[![Build Status](https://www.travis-ci.org/houbb/word-checker.svg?branch=master)](https://www.travis-ci.org/houbb/word-checker?branch=master)
[![Coverage Status](https://coveralls.io/repos/github/houbb/word-checker/badge.svg?branch=master)](https://coveralls.io/github/houbb/word-checker?branch=master)
[![](https://img.shields.io/badge/license-Apache2-FF0080.svg)](https://github.com/houbb/word-checker/blob/master/LICENSE.txt)
[![Open Source Love](https://badges.frapsoft.com/os/v2/open-source.svg?v=103)](https://github.com/houbb/word-checker)

# Feature description

### Support English word correction

- You can quickly determine whether the current word is spelled incorrectly

- Can return the best match result

- You can return to the corrected matching list, support specifying the size of the returned list

- Error message support i18n

- Support uppercase and lowercase, full-width and half-width formatting

- Support custom thesaurus

### Support basic Chinese spelling check

# Change log

> [Change Log](https://github.com/houbb/word-checker/blob/master/CHANGELOG.md)

# Quick start

## JDK version

Jdk 1.7+

## maven introduction

```xml
<dependency>
     <groupId>com.github.houbb</groupId>
     <artifactId>word-checker</artifactId>
    <version>0.0.7</version>
</dependency>
```

### gradle introduction

```
compile group:'com.github.houbb', name:'word-checker', version: '0.0.7'
```

## Test Case

According to the input, the best correction result is automatically returned.

```java
final String speling = "speling";
Assert.assertEquals("spelling", EnWordCheckers.correct(speling));
```

# Core api introduction

The core api is under the `EnWordCheckers` tool class.

| Function | Method | Parameters | Return Value | Remarks |
|:----|:----|:----|:---|:----|
| Determine whether the spelling of the word is correct | isCorrect(string) | The word to be detected | boolean | |
| Return the best corrected result | correct(string) | The word to be detected | String | If no word that can be corrected is found, then return itself |
| Determine whether the spelling of the word is correct | correctList(string) | The word to be detected | List<String> | Return a list of all matching corrections |
| Determine whether the spelling of the word is correct | correctList(string, int limit) | The word to be detected, the size of the returned list | Return the corrected list of the specified size | List size <= limit |

## Test example

> See [EnWordCheckerTest.java](https://github.com/houbb/word-checker/tree/master/src/test/java/com/github/houbb/word/checker/util/EnWordCheckersTest.java)

## Is the spelling correct?

```java
final String hello = "hello";
final String speling = "speling";
Assert.assertTrue(EnWordCheckers.isCorrect(hello));
Assert.assertFalse(EnWordCheckers.isCorrect(speling));
```

## Return the best match result

```java
final String hello = "hello";
final String speling = "speling";
Assert.assertEquals("hello", EnWordCheckers.correct(hello));
Assert.assertEquals("spelling", EnWordCheckers.correct(speling));
```

## Corrected the match list by default

```java
final String word = "goo";
List<String> stringList = EnWordCheckers.correctList(word);
Assert.assertEquals("[go, good, too, god, got, oo, goot, foo]", stringList.toString());
```

## Specify the size of the corrected match list

```java
final String word = "goo";
final int limit = 2;
List<String> stringList = EnWordCheckers.correctList(word, limit);
Assert.assertEquals("[go, good]", stringList.toString());
```

# Chinese spelling correction

## Core api

In order to reduce learning costs, the core api and `ZhWordCheckers` are consistent with English spelling detection.

## Is the spelling correct?

```java
final String right = "正确";
final String error = "万变不离其中";

Assert.assertTrue(ZhWordCheckers.isCorrect(right));
Assert.assertFalse(ZhWordCheckers.isCorrect(error));
```

## Return the best match result

```java
final String right = "正确";
final String error = "万变不离其中";

Assert.assertEquals("正确", ZhWordCheckers.correct(right));
Assert.assertEquals("万变不离其宗", ZhWordCheckers.correct(error));
```

## Corrected the match list by default

```java
final String word = "万变不离其中";

List<String> stringList = ZhWordCheckers.correctList(word);
Assert.assertEquals("[万变不离其宗]", stringList.toString());
```

## Specify the size of the corrected match list

```java
final String word = "万变不离其中";
final int limit = 1;

List<String> stringList = ZhWordCheckers.correctList(word, limit);
Assert.assertEquals("[万变不离其宗]", stringList.toString());
```

# Formatting

Sometimes the user's input is various, this tool supports the processing of formatting.

## Case

Uppercase will be uniformly formatted as lowercase.

```java
final String word = "stRing";

Assert.assertTrue(EnWordCheckers.isCorrect(word));
```

## Full-width half-width

Full-width will be uniformly formatted as half-width.

```java
final String word = "string";

Assert.assertTrue(EnWordCheckers.isCorrect(word));
```

# Custom English Thesaurus

## File configuration

You can create the file `resources/data/define_word_checker_en.txt` in the project resource directory

The content is as follows:

```
my-long-long-define-word,2
my-long-long-define-word-two
```

Different words are on their own lines.

The first column of each row represents the word, and the second column represents the number of occurrences, separated by a comma `,`.

The greater the number of times, the higher the return priority when correcting. The default value is 1.

User-defined thesaurus has a higher priority than the built-in thesaurus of the system.

## Test code

After we specify the corresponding word, the spelling check will take effect.

```java
final String word = "my-long-long-define-word";
final String word2 = "my-long-long-define-word-two";

Assert.assertTrue(EnWordCheckers.isCorrect(word));
Assert.assertTrue(EnWordCheckers.isCorrect(word2));
```

# Custom Chinese Thesaurus

## File configuration

You can create the file `resources/data/define_word_checker_zh.txt` in the project resource directory

The content is as follows:

```
默守成规 墨守成规
```

Use English spaces to separate, the front is wrong, and the back is correct.

# Late Road-Map

- Support English word segmentation and process the entire English sentence

- Support Chinese word segmentation spelling detection

- Introduce Chinese error correction algorithm, homophone characters and similar characters processing.

- Support Chinese and English mixed spelling detection

# Technical Acknowledgements

[Words](https://github.com/atebits/Words) provides raw English word data.