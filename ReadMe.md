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
git clone https://github.com/N0WST4NDUP/openlaw-mcp-server.git
cd openlaw-mcp-server

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

## 작업 진행도

```
╭────────────────────────────────────────────────────────────────────────────╮
│                                                                            │
│                              🚀 프로젝트 현황                               │
│                                                                            │
│ 진행률: [████████████░░░░░░░░░░░░░░░░░░] 40%                               │
│                                                                            │
│ 완료: 6 | 진행 중: 1 | 대기 중: 8 | 차단됨: 0 | 연기됨: 0 | 취소됨: 0        │
│                                                                            │
│ 서브태스크 진행률: [████░░░░░░░░░░░░░░░░░░░░░░░░░░] 14%                     │
│ 완료: 1/7 | 진행 중: 1 | 대기 중: 5                                         │
│                                                                            │
╰────────────────────────────────────────────────────────────────────────────╯

╭────────────────────────────────────────────────────────────────────────────╮
│                                                                            │
│                              📊 우선순위 분석                               │
│                                                                            │
│ 🔴 높음: 8 | 🟡 중간: 7 | 🟢 낮음: 0                                        │
│                                                                            │
│ 다음 작업: 5.2 - Implement Law Content Model Classes (우선순위: 높음)       │
│                                                                            │
╰────────────────────────────────────────────────────────────────────────────╯

## 작업 목록

| ID   | 제목                                      | 상태        | 우선순위 | 의존성        |
|------|-------------------------------------------|------------|----------|--------------|
| 1    | Setup Project Repository and Basic Structure  | ✅ 완료     | 높음     | 없음          |
| 2    | Implement National Law API Integration Framework | ✅ 완료     | 높음     | 1            |
| 3    | Define Data Models for Law Information    | ✅ 완료     | 높음     | 1            |
| 4    | Implement Law List Search Functionality   | ✅ 완료     | 중간     | 2, 3         |
| 5    | Implement Law Content Search Functionality| 🔄 진행 중  | 높음     | 2, 3         |
| 5.1  | └─ Create National Law API Client Service | ✅ 완료     | -       | 없음          |
| 5.2  | └─ Implement Law Content Model Classes    | 🔄 진행 중  | -       | 없음          |
| 5.3  | └─ Develop Law Content Parser             | ⏳ 대기 중  | -       | 5.1, 5.2     |
| 5.4  | └─ Implement Cross-Reference Resolution   | ⏳ 대기 중  | -       | 5.3          |
| 5.5  | └─ Create Law Content Controller          | ⏳ 대기 중  | -       | 5.3          |
| 5.6  | └─ Implement Section-Specific Retrieval Logic | ⏳ 대기 중 | -      | 5.3, 5.5     |
| 5.7  | └─ Implement Comprehensive Error Handling | ⏳ 대기 중  | -       | 5.5, 5.6     |
| 6    | Implement Case Precedent Search Functionality | ⏳ 대기 중 | 중간    | 2, 3, 5      |
| 7    | Implement Litigation Procedure Guidance Functionality | ⏳ 대기 중 | 중간 | 3, 5, 6   |
| 8    | Implement SpringAI Integration for LLM Models | ✅ 완료   | 높음     | 1            |
| 9    | Implement JSON-RPC MCP Tool Interface     | ⏳ 대기 중  | 높음     | 4, 5, 6, 7, 8 |
| 10   | Implement AI Prompt Engineering for Legal Assistance | ⏳ 대기 중 | 중간 | 8      |
| 11   | Implement Data Normalization Layer        | ⏳ 대기 중  | 높음     | 2, 3         |
| 12   | Implement Caching Mechanism               | ⏳ 대기 중  | 중간     | 4, 5, 6      |
| 13   | Implement Docker Containerization         | ✅ 완료     | 중간     | 1            |
| 14   | Implement Comprehensive Error Handling and Logging | ⏳ 대기 중 | 중간 | 2, 4, 5, 6, 7, 8, 9 |
| 15   | Implement Disclaimer and Legal Notice System | ⏳ 대기 중 | 높음    | 9, 10        |
```

> 본 작업 진행도는 [Taskmaster](https://github.com/taskmaster-ai/taskmaster) 도구를 사용하여 자동으로 생성한 내용입니다. 프로젝트의 실시간 진행 상황을 반영합니다.
