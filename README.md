# 项目简介

本项目用于单词拼写检查。

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.houbb/word-checker/badge.svg)](http://mvnrepository.com/artifact/com.github.houbb/word-checker)
[![Build Status](https://www.travis-ci.org/houbb/word-checker.svg?branch=master)](https://www.travis-ci.org/houbb/word-checker?branch=master)
[![Coverage Status](https://coveralls.io/repos/github/houbb/word-checker/badge.svg?branch=master)](https://coveralls.io/github/houbb/word-checker?branch=master)


# 特性说明

## 支持 i18n

错误提示支持 i18N

## 支持英文的单词纠错

- 可以迅速判断当前单词是否拼写错误

- 可以返回最佳匹配结果

- 可以返回纠正匹配列表，支持指定返回列表的大小


## 后续将会添加的新功能

- 英文单词支持自行定义

- 中文单词的拼写是否正确功能添加 

# 快速开始

## JDK 版本

JDK1.7 及其以后

## 入门例子

### maven 引入

本项目已经上传到 maven 仓库，直接引入即可

```xml
<dependency>
     <groupId>com.github.houbb</groupId>
     <artifactId>word-checker</artifactId>
    <version>0.0.1</version>
</dependency>
```

### 测试案例

- Main.java

```java
public static void main(String[] args) {
    final String result = EnWordChecker.getInstance().correct("speling");
    System.out.println(result);
}
```

结果为 

```
spelling
```

# 英文拼写纠错功能介绍

> 备注 

所有方法为 `EnWordChecker` 类下。

| 功能 | 方法 | 参数 | 返回值 | 备注 |
|:----|:----|:----|:---|:----|
| 判断单词拼写是否正确 | isCorrect(string) | 待检测的单词 | boolean | |
| 返回最佳纠正结果 | correct(string) | 待检测的单词 | String | 如果没有找到可以纠正的单词，则返回其本身 |
| 判断单词拼写是否正确 | correctList(string) | 待检测的单词 | List<String> | 返回所有匹配的纠正列表 |
| 判断单词拼写是否正确 | correctList(string, int limit) | 待检测的单词, 返回列表的大小 | 返回指定大小的的纠正列表 | 列表大小 <= limit |

## 测试例子

> 参见 []()

```java
/**
 * 是否拼写正确
 */
@Test
public void isCorrectTest() {
    final String hello = "hello";
    final String speling = "speling";
    Assert.assertTrue(EnWordChecker.getInstance().isCorrect(hello));
    Assert.assertFalse(EnWordChecker.getInstance().isCorrect(speling));
}
```

```java
/**
* 返回最佳匹配结果
*/
@Test
public void correctTest() {
    final String hello = "hello";
    final String speling = "speling";
    Assert.assertEquals("hello", EnWordChecker.getInstance().correct(hello));
    Assert.assertEquals("spelling", EnWordChecker.getInstance().correct(speling));
}
```

```java
/**
 * 默认纠正匹配列表
 * 1. 默认返回所有
 */
@Test
public void correctListTest() {
    final String word = "goo";
    List<String> stringList = EnWordChecker.getInstance().correctList(word);
    Assert.assertTrue(stringList.size() > 0);
}
```

```java
/**
 * 默认纠正匹配列表
 * 1. 默认返回所有
 */
@Test
public void correctListTest() {
    final String word = "goo";
    List<String> stringList = EnWordChecker.getInstance().correctList(word);
    Assert.assertTrue(stringList.size() > 0);
}
```


# 技术鸣谢

[Words](https://github.com/atebits/Words) 提供的原始**英语单词**数据。

# 文档参考

> [ENABLE word list](https://everything2.com/title/ENABLE+word+list)

> [spell-correct](http://norvig.com/spell-correct.html)

> [spellchecking](http://www.dcs.bbk.ac.uk/~roger/spellchecking.html)

