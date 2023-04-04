# 项目简介

> [英文文档](README_EN.md)

本项目用于单词拼写检查。支持英文单词拼写检测，和中文拼写检测。

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.houbb/word-checker/badge.svg)](http://mvnrepository.com/artifact/com.github.houbb/word-checker)
[![Build Status](https://www.travis-ci.org/houbb/word-checker.svg?branch=master)](https://www.travis-ci.org/houbb/word-checker?branch=master)
[![Coverage Status](https://coveralls.io/repos/github/houbb/word-checker/badge.svg?branch=master)](https://coveralls.io/github/houbb/word-checker?branch=master)
[![](https://img.shields.io/badge/license-Apache2-FF0080.svg)](https://github.com/houbb/word-checker/blob/master/LICENSE.txt)
[![Open Source Love](https://badges.frapsoft.com/os/v2/open-source.svg?v=103)](https://github.com/houbb/word-checker)

# 特性说明

### 支持英文的单词纠错

- 可以迅速判断当前单词是否拼写错误

- 可以返回最佳匹配结果

- 可以返回纠正匹配列表，支持指定返回列表的大小

- 错误提示支持 i18n

- 支持大小写、全角半角格式化处理

- 支持自定义词库

- 内置 27W+ 的英文词库

### 支持基本的中文拼写检测

# 变更日志

> [变更日志](https://github.com/houbb/word-checker/blob/master/CHANGELOG.md)

# 快速开始

## JDK 版本

Jdk 1.7+

## maven 引入

```xml
<dependency>
     <groupId>com.github.houbb</groupId>
     <artifactId>word-checker</artifactId>
    <version>1.0.0</version>
</dependency>
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
final String word = "goox";
List<String> stringList = EnWordCheckers.correctList(word);
Assert.assertEquals("[good, goo, goon, goof, gook, goop, goos, gox, goog, gool, goor]", stringList.toString());
```

## 指定纠正匹配列表大小

```java
final String word = "goox";
final int limit = 2;
List<String> stringList = EnWordCheckers.correctList(word, limit);
Assert.assertEquals("[good, goo]", stringList.toString());
```

# 中文拼写纠正

## 核心 api

为降低学习成本，核心 api 和 `ZhWordCheckers` 中，和英文拼写检测保持一致。

## 是否拼写正确

```java
final String right = "正确";
final String error = "万变不离其中";

Assert.assertTrue(ZhWordCheckers.isCorrect(right));
Assert.assertFalse(ZhWordCheckers.isCorrect(error));
```

## 返回最佳匹配结果

```java
final String right = "正确";
final String error = "万变不离其中";

Assert.assertEquals("正确", ZhWordCheckers.correct(right));
Assert.assertEquals("万变不离其宗", ZhWordCheckers.correct(error));
```

## 默认纠正匹配列表

```java
final String word = "万变不离其中";

List<String> stringList = ZhWordCheckers.correctList(word);
Assert.assertEquals("[万变不离其宗]", stringList.toString());
```

## 指定纠正匹配列表大小

```java
final String word = "万变不离其中";
final int limit = 1;

List<String> stringList = ZhWordCheckers.correctList(word, limit);
Assert.assertEquals("[万变不离其宗]", stringList.toString());
```

# 长文本中英文混合

## 情景

实际拼写纠正的话，最佳的使用体验是用户输入一个长文本，并且可能是中英文混合的。

然后实现上述对应的功能。

## 核心方法

`WordCheckers` 工具类提供了长文本中英文混合的自动纠正功能。

| 功能 | 方法 | 参数 | 返回值 | 备注 |
|:----|:----|:----|:---|:----|
| 文本拼写是否正确 | isCorrect(string) | 待检测的文本 | boolean | 全部正确，才会返回 true |
| 返回最佳纠正结果 | correct(string) | 待检测的单词 | String | 如果没有找到可以纠正的文本，则返回其本身 |
| 判断文本拼写是否正确 | correctMap(string) | 待检测的单词 | `Map<String, List<String>>` | 返回所有匹配的纠正列表 |
| 判断文本拼写是否正确 | correctMap(string, int limit) | 待检测的文本, 返回列表的大小 | 返回指定大小的的纠正列表 | 列表大小 <= limit |

### 拼写是否正确

```java
final String hello = "hello 你好";
final String speling = "speling 你好 以毒功毒";
Assert.assertTrue(WordCheckers.isCorrect(hello));
Assert.assertFalse(WordCheckers.isCorrect(speling));
```

### 返回最佳纠正结果

```java
final String hello = "hello 你好";
final String speling = "speling 你好以毒功毒";
Assert.assertEquals("hello 你好", WordCheckers.correct(hello));
Assert.assertEquals("spelling 你好以毒攻毒", WordCheckers.correct(speling));
```

### 判断文本拼写是否正确

每一个词，对应的纠正结果。

```java
final String hello = "hello 你好";
final String speling = "speling 你好以毒功毒";
Assert.assertEquals("{hello=[hello],  =[ ], 你=[你], 好=[好]}", WordCheckers.correctMap(hello).toString());
Assert.assertEquals("{ =[ ], speling=[spelling, spewing, sperling, seeling, spieling, spiling, speeling, speiling, spelding], 你=[你], 好=[好], 以毒功毒=[以毒攻毒]}", WordCheckers.correctMap(speling).toString());
```

### 判断文本拼写是否正确

同上，指定最多返回的个数。

```java
final String hello = "hello 你好";
final String speling = "speling 你好以毒功毒";

Assert.assertEquals("{hello=[hello],  =[ ], 你=[你], 好=[好]}", WordCheckers.correctMap(hello, 2).toString());
Assert.assertEquals("{ =[ ], speling=[spelling, spewing], 你=[你], 好=[好], 以毒功毒=[以毒攻毒]}", WordCheckers.correctMap(speling, 2).toString());
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

# 自定义英文词库

## 文件配置

你可以在项目资源目录创建文件 `resources/data/define_word_checker_en.txt`

内容如下：

```
my-long-long-define-word,2
my-long-long-define-word-two
```

不同的词独立一行。

每一行第一列代表单词，第二列代表出现的次数，二者用逗号 `,` 隔开。

次数越大，在纠正的时候返回优先级就越高，默认值为 1。

用户自定义的词库优先级高于系统内置词库。

## 测试代码

我们在指定了对应的单词之后，拼写检测的时候就会生效。

```java
final String word = "my-long-long-define-word";
final String word2 = "my-long-long-define-word-two";

Assert.assertTrue(EnWordCheckers.isCorrect(word));
Assert.assertTrue(EnWordCheckers.isCorrect(word2));
```

# 自定义中文词库

## 文件配置

你可以在项目资源目录创建文件 `resources/data/define_word_checker_zh.txt`

内容如下：

```
默守成规 墨守成规
```

使用英文空格分隔，前面是错误，后面是正确。

# 后期 Road-Map

- 支持英文分词，处理整个英文句子

- 支持中文分词拼写检测

- 引入中文纠错算法，同音字和形近字处理。

- 支持中英文混合拼写检测

# 技术鸣谢

[Words](https://github.com/atebits/Words) 提供的原始英语单词数据。

# ROAD-MAP

- [x] 支持长文本的自动纠正能力

- [x] 中英文混合的纠正

- [x] 指定是否忽略大小写

- [x] 指定是否忽略全角半角