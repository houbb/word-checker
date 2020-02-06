# 项目简介

本项目用于单词拼写检查。

目前支持英文单词拼写检测，后期将引入中文拼写检测。

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.houbb/word-checker/badge.svg)](http://mvnrepository.com/artifact/com.github.houbb/word-checker)
[![Build Status](https://www.travis-ci.org/houbb/word-checker.svg?branch=master)](https://www.travis-ci.org/houbb/word-checker?branch=master)
[![Coverage Status](https://coveralls.io/repos/github/houbb/word-checker/badge.svg?branch=master)](https://coveralls.io/github/houbb/word-checker?branch=master)

# 特性说明

### 支持英文的单词纠错

- 可以迅速判断当前单词是否拼写错误

- 可以返回最佳匹配结果

- 可以返回纠正匹配列表，支持指定返回列表的大小

- 错误提示支持 i18n

- 支持大小写、全角半角格式化处理

### v0.0.3 最新变更

- 引入工具类，优化用户使用体验

- 支持英文忽略全角半角

- 代码结构优化，便于后期拓展

> [变更日志](https://github.com/houbb/word-checker/blob/master/CHANGELOG.md)

# 快速开始

## JDK 版本

Jdk 1.7+

## maven 引入

```xml
<dependency>
     <groupId>com.github.houbb</groupId>
     <artifactId>word-checker</artifactId>
    <version>0.0.3</version>
</dependency>
```

### gradle 引入

```
compile group: 'com.github.houbb', name: 'word-checker', version: '0.0.3'
```

## 测试案例

会根据输入，自动返回最佳纠正结果。

```java
final String speling = "speling";
Assert.assertEquals("spelling", EnWordCheckers.correct(speling));
```

# 核心 api 介绍

核心 api 在 `EnWordCheckers` 工具类下。

| 功能 | 方法 | 参数 | 返回值 | 备注 |
|:----|:----|:----|:---|:----|
| 判断单词拼写是否正确 | isCorrect(string) | 待检测的单词 | boolean | |
| 返回最佳纠正结果 | correct(string) | 待检测的单词 | String | 如果没有找到可以纠正的单词，则返回其本身 |
| 判断单词拼写是否正确 | correctList(string) | 待检测的单词 | List<String> | 返回所有匹配的纠正列表 |
| 判断单词拼写是否正确 | correctList(string, int limit) | 待检测的单词, 返回列表的大小 | 返回指定大小的的纠正列表 | 列表大小 <= limit |

## 测试例子

> 参见 [EnWordCheckerTest.java](https://github.com/houbb/word-checker/tree/master/src/test/java/com/github/houbb/word/checker/util/EnWordCheckersTest.java)

## 是否拼写正确

```java
final String hello = "hello";
final String speling = "speling";
Assert.assertTrue(EnWordCheckers.isCorrect(hello));
Assert.assertFalse(EnWordCheckers.isCorrect(speling));
```

## 返回最佳匹配结果

```java
final String hello = "hello";
final String speling = "speling";
Assert.assertEquals("hello", EnWordCheckers.correct(hello));
Assert.assertEquals("spelling", EnWordCheckers.correct(speling));
```

## 默认纠正匹配列表

```java
final String word = "goo";
List<String> stringList = EnWordCheckers.correctList(word);
Assert.assertEquals("[go, good, too, god, got, oo, goot, foo]", stringList.toString());
```

## 指定纠正匹配列表大小

```java
final String word = "goo";
final int limit = 2;
List<String> stringList = EnWordCheckers.correctList(word, limit);
Assert.assertEquals("[go, good]", stringList.toString());
```

# 格式化处理

有时候用户的输入是各式各样的，本工具支持对于格式化的处理。

## 大小写

大写会被统一格式化为小写。

```java
final String word = "stRing";

Assert.assertTrue(EnWordCheckers.isCorrect(word));
```

## 全角半角

全角会被统一格式化为半角。

```java
final String word = "stｒing";

Assert.assertTrue(EnWordCheckers.isCorrect(word));
```

# 后期 Road-Map

- 支持用户自定义词库

- 支持英文分词，处理整个英文句子

- 支持中文拼写检测

# 技术鸣谢

[Words](https://github.com/atebits/Words) 提供的原始英语单词数据。

## 文档参考

> [ENABLE word list](https://everything2.com/title/ENABLE+word+list)

> [spell-correct](http://norvig.com/spell-correct.html)

> [spellchecking](http://www.dcs.bbk.ac.uk/~roger/spellchecking.html)

