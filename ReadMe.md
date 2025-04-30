# OpenLaw MCP 서버

Spring Boot 기반 Model Context Protocol(MCP) 서버로 국가법령정보 Open API를 활용한 법령 검색 기능을 제공합니다.

## 특징

- 법령 검색 기능
- 행정규칙 검색 기능
- 자치법규 검색 기능

> **참고**: 이 서버는 국가법령정보 Open API에 접근하기 위한 인증 키가 필요합니다.  
> 자세한 사항은 https://open.law.go.kr/LSO/information/guide.do 를 참조 해주세요.

## API

### :hammer_and_wrench: Tool

- **law_search**

  - 국가법령정보 Open API를 통해 법령, 행정규칙, 자치법규를 검색
  - Input:
    - `target` (string, required): 서비스대상(법령:law/행정규칙:admrul/자치법규:ordin)
    - `search` (integer, optional): 검색범위(1:법령명/2:본문, default=1)
    - `query` (string, optional): 검색범위에서 검색을 원하는 질의(e.g. 대한민국헌법)
    - `display` (integer, optional): 검색 결과 개수(default=20 max=100)
    - `page` (integer, optional): 검색 결과 페이지(default=1)
  - Output:
    - 법령 목록 정보 (JSON 형식)

- **law_service**

  - (개발 중) 국가법령정보 Open API를 통한 상세 법령 서비스

### :open_file_folder: Resource

-

### :page_facing_up: Prompt

-

## 설치 및 실행

### 준비물

- `Docker`: Provider 컨테이너를 올리기 위한 도커
- `Image`: 직접 빌드하거나 다운받은 이미지
- `OPENLAW_OC`: 국가법령정보 Open API 인증 키

### Git Clone

```bash
# Git Clone


# Image Build
docker build -t n0wst4ndup/openlaw-mcp .
```

### Docker Pull

```bash
docker pull n0wst4ndup/openlaw-mcp
```

## Host

Host Mcp 설정에 다음을 입력하세요:

### Mac/Linux

```json
{
  "mcpServers": {
    "openlaw": {
      "command": "docker",
      "args": [
        "run",
        "-i",
        "--rm",
        "-e",
        "OPENLAW_OC=your_api_key",
        "n0wst4ndup/openlaw-mcp"
      ]
    }
  }
}
```

### Windows

```json
{
  "mcpServers": {
    "openlaw": {
      "command": "cmd",
      "args": [
        "/c",
        "docker",
        "run",
        "-i",
        "--rm",
        "-e",
        "OPENLAW_OC=your_api_key",
        "n0wst4ndup/openlaw-mcp"
      ]
    }
  }
}
```
