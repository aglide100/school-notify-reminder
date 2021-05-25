## School-notify-reminder

이 레포는 1학기 클래스 프로젝트 결과물입니다.

본 어플리케이션은 java로 작성되었습니다.

---

## 프로젝트의 주요 목적

본 프로젝트는 놓치기 쉬운 게시글이나 특정 사이트가 변경될 시 사용자에게 알려주는 것을 목표로 삼고 있습니다.

---

## 프로젝트 진행시 공통되는 약속

본 프로젝트는 수업과 진행되는 과제물이고 처음 안드로이드를 접하기에 코드 관리와 git 사용에 대한 약속이 있습니다.

- 코드 패턴이용

  > 현재 mvp패턴을 이용하여 진행중입니다.


- Pull request사용
  > 현제 메인 브런치는 새롭게 병합될때 마다 붙어있는 tag가 변경됩니다. 그래서 수정사항이 생길 경우에는 새로운 브런치를 생성후 pull request사용을 권합니다.

---

## 브런치 관리

Git flow을 기반으로 release, develop, hotfix와 같이 여러 주요 브런치를 두지 않고 브런치를 관리하고 있습니다.

정말 간단하지만 수정사항이 있을 경우 새로운 브런치를 생성한 검토 후 메인 브런치에 병합하는 것이 브런치 관리의 목표로 두고 있습니다.

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
