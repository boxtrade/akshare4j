# akshare4j




# BASE 
--- 
# Java Code Automation Series Demo 一 JAR automatically published to GitHub Packages 【 java 代码自动化系列专题一jar包自动化发布到GitHub Packages】
> The current blog link [ codeAuto4j-jar](https://github.com/boxtrade/codeAuto4j-jar)
---
> Home directory 主目录 [Introduction to the Java Code Automation Series Demo 【java 代码自动化系列专题】](https://github.com/boxtrade/codeAuto4j)
---
# 项目目标 Project Target
提供一个最简版的jar 自动发布到github packages 的样板案例

Provide a minimal version of the sample case of JAR automatically published to GitHub Packages


# 项目初始化 Project initialization
```shell
git init
git add *
git commit -m "first commit"
git branch -M main
git remote add origin git@github.com:boxtrade/codeAuto4j-jar.git
git push -u origin main
```
# GITHUB Action publish JAR shell
[maven-publish.yml](.github/workflows/maven-publish.yml)
```github-action
# This workflow will build a package using Maven and then publish it to GitHub packages when a release is created
# For more information see: https://github.com/actions/setup-java/blob/main/docs/advanced-usage.md#apache-maven-with-a-settings-path

name: Maven Package

on:
  push

jobs:
  build:

    runs-on: ubuntu-latest
    permissions:
      contents: read
      packages: write

    steps:
    - uses: actions/checkout@v3
    - name: Set up JDK 8
      uses: actions/setup-java@v3
      with:
        java-version: '8'
        distribution: 'temurin'
        server-id: github # Value of the distributionManagement/repository/id field of the pom.xml
        settings-path: ${{ github.workspace }} # location for the settings.xml file

    - name: Build with Maven
      run: mvn -B package --file pom.xml

    - name: Publish to GitHub Packages Apache Maven
      run: mvn deploy -s $GITHUB_WORKSPACE/settings.xml
      env:
        GITHUB_TOKEN: ${{ github.token }}

```
# FAQ 常见问题
## 1、 Error:  Failed to execute goal org.apache.maven.plugins:maven-deploy-plugin:2.8.2:deploy (default-deploy) on project codeAuto4j-jar: Deployment failed: repository element was not specified in the POM inside distributionManagement element or in -DaltDeploymentRepository=id::layout::url parameter -> [Help 1]
> solution: pom add distributionManagement ；
```pom

    <distributionManagement>
        <repository>
            <id>github</id>
            <name>GitHub Packages</name>
            <url>https://maven.pkg.github.com/boxtrade/codeAuto4j-jar</url>
        </repository>
    </distributionManagement>


```
## 2、Error:  Failed to execute goal org.apache.maven.plugins:maven-deploy-plugin:2.8.2:deploy (default-deploy) on project codeAuto4j-jar: Failed to deploy artifacts: Could not transfer artifact com.boxtrade:codeAuto4j-jar:jar:1.0-20230831.190619-1 from/to github (https://maven.pkg.github.com/boxtrade/codeAuto4j-jar): transfer failed for https://maven.pkg.github.com/boxtrade/codeAuto4j-jar/com/boxtrade/codeAuto4j-jar/1.0-SNAPSHOT/codeAuto4j-jar-1.0-20230831.190619-1.jar, status: 422 Unprocessable Entity -> [Help 1]
> solution: artifacId should be lowercase to be able to push to github packages ;artifacId 应该是小写的，以便能够推送到 github 包
- fix e7c64b516edef70b337715066fb30965ed8ebef9
> <artifactId>codeAuto4j-jar</artifactId> to     <artifactId>codeauto4j-jar</artifactId>
- https://github.com/orgs/community/discussions/26328#discussioncomment-3251482
- https://github.com/orgs/community/discussions/23474?sort=top#discussioncomment-4611823
