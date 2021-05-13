## School-notify-reminder

이 레포는 1학기 클래스 프로젝트 결과물입니다.

본 어플리케이션은 java로 작성되었습니다.

---

## 프로젝트의 주요 목적

본 프로젝트는 놓치기 쉬운 게시글(아직은 특정 게시글만 생각중입니다.)을 사용자에게 알려주는 것이 주된 목적입니다.

---

## 프로젝트 진행시 공통되는 약속

본 프로젝트는 본교 수업과 진행되는 과제물이고 처음 안드로이드를 접하는 부분이기에 코드 관리와 git 사용에 대한 약속이 있습니다.

- 코드 패턴이용

  > 현재 mvp패턴을 이용하여 진행중입니다.

- Pull request사용
  > 하단에서 언급할 Git flow처럼 브런치를 세세하게 나눠서 관리하지는 않을 것이지만 메인 브런치로 바로 merge하는 일 없게 pr을 이용하여 코드를 작성해주시기 바랍니다.

---

## 브런치 관리

Git flow처럼 release, develop, hotfix와 같이 여러 브런치를 두지 않고 브런치를 관리하고 있습니다.

브런치 관리라고 뭐라 부르기가 힘들지만 모든 코드는 pull request를 통해 메인 브런치에 merge하는 것을 공통된 약속으로 잡고 있습니다. (애초에 main branch에 tag가 계속 바꿔이서 바로 push하기가 쉽지 않습니다.)

---

## 화면 설계

기본적인 구성은 피그마를 통해서 만들고 세세한 부분은 조금씩 변경 될 수 있습니다.

> [figmalink]

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
