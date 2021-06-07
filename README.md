## School-notify-reminder

이 레포는 1학기 클래스 프로젝트 결과물입니다.

본 어플리케이션은 java로 작성되었습니다.

---

## 프로젝트의 주요 목적

본 프로젝트는 놓치기 쉬운 게시글이나 특정 사이트가 변경될 시 사용자에게 알려주는 것을 목표로 삼고 있습니다.

---

## 프로젝트 진행시 지켯으면 하는 약속

본 프로젝트는 본교 수업과 진행되는 과제물이며 git을 처음 접하는 분도 계시기에 아래의 사항을 지켜주시면 감사하겠습니다.


- 코드 패턴이용

  > 현재 mvp패턴을 이용하여 작업중입니다.


- Pull request사용
  > 하단에서 언급할 Git flow처럼 브런치를 세세하게 나눠서 관리하지는 않을 것이지만 메인 브런치로 바로 merge하는 일 없게 pr을 이용하여 코드를 작성해주시기 바랍니다.


---

## 브런치 관리

Git flow을 기반으로 release, develop, hotfix와 같이 여러 주요 브런치를 두지 않고 브런치를 관리하고 있습니다.


---

## Git 사용법(CLI 기준)

> 0. git clone {원격지 주소}

> 1. git pull

현 저장소 최신화

> 2. git branch

현재 브런치 확인

> 3. git checkout -b {브런치 이름}

새로운 브런치 생성

> 4. git add .

변경된 파일을 캐시로 올림

> 5. git commit -m "커밋 메시지"

커밋 및 메시지 작성

> 6. git status

[옵션] 혹시 빠진 부분과 같은 것을 확인

> 7. git push

or

> 7. git push origin {현 브런치 이름}

---

## Git 사용법(android studio)

처음 시작시

> 1. 프로젝트 시작시 원격 저장소에서 받아옴(file탭에서 new -> project from VCS)

> 2. Repository URL

> 3. Clone

작업후

> 4. VCS탭 -> Git -> pull

저장소 최신화

> 5. VCS탭 -> Git -> branches -> New Branch

새로운 브런치 생성

> 6. VCS탭 -> Git -> add

변경된 파일 캐시에 올림

> 7. VCS탭 -> Git -> commit

커밋 및 메시지작성

> 8. VCS탭 -> Git -> push


---

## 화면 설계

기본적인 구성은 카카오 오븐을 통해서 만들고 세세한 부분은 조금씩 변경 될 수 있습니다.

<del>[figmalink]</del>

---

## CI

현재 CI툴은 github action을 이용하고 있습니다. googlePlay에 등록하지 않았기에 배포하는 부분은 없습니다.

간단하게 빌드만 하고 apk를 릴리즈하는 하는 것을 주된 목표로 하고 있습니다.

릴리즈는 여기서 확인 하실 수 있습니다.

> [releaseLink]

---

## Dependency

> create apk and release [ciLink]

[releaselink]: https://github.com/aglide100/school-notify-reminder/releases
[figmalink]: https://www.figma.com/file/GLhBBLaehuzTEQfwNh9zG8/class-project?node-id=0%3A1
[cilink]: https://github.com/ShaunLWM/action-release-debugapk
