## npm 관련

### 모듈
```npm insall```

### ck에디터
```npm install --save @ckeditor/ckeditor5-react @ckeditor/ckeditor5-build-classic```

### 리액트 아이콘
```npm install react-icons --save```

### 스타일 컴포넌트
```npm install --save styled-components```

### 추가
```npm install react-cookie``` <br>
```npm install react-router-dom```

-----

09/01(금) : 수인 
<br>
```
Main.js (메인)
├─ Navbar.js (네비)
└─ Posts.js (글목록)
             ├─ AskQuestion.js (질문등록)
             ├─ page/Vote.js (투표버튼)
             └─ page/Post.js (질문+답변)
         <!> ├─ Question.js (답변등록) <연결을 잘못해서 오류발생(임시로 주석처리)>
```
<br>

#### 추가된 것

1. page폴더 임시생성. ( /questions 역활 )<br>
   └─ Questions.js 삭제 (Questions.js === page/Post.js)<br><br>
2. AskQuestion.js 이동. ( Posts.js 안에있음)
<br><br>
#### 더 수정해야 할 것

1. css 수정.(연결하면서 뒤틀림) <br>
2. API url에 맞게 링크달기. <br>
3. 답변등록 연결.
