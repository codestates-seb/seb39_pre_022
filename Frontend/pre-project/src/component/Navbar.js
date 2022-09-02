/* eslint-disable jsx-a11y/alt-text */
import React, { useState } from "react";
import {
  NavbarContainer,
  LeftContainer,
  RightContainer,
  NavbarInnerContainer,
  NavbarLink,
  OpenLinksButton,

} from "../styles/NavbarStyle";
import LogoImg from "../img/logo.png";

function Navbar({ email, ok }) {
  const [extendNavbar, setExtendNavbar] = useState(false);
  const [search, setSearch] = useState("");
  return (
    <NavbarContainer extendNavbar={extendNavbar}>
      <NavbarInnerContainer>
        <LeftContainer>
          <NavbarLink to="/"><img src={LogoImg} /></NavbarLink>
          <NavbarLink className="inputContainer" to="/search">
            <input
              type="text"
              className="searchForm"
              placeholder="Search"
              onChange={e => setSearch(e.target.value)}
            />
            {/* {console.log(search)} */}
          </NavbarLink>

          <OpenLinksButton /* 사이즈가 작아졌을 때 나타나는 버거메뉴 */
            onClick={() => {
              setExtendNavbar((curr) => !curr);
            }}
          >
            {extendNavbar ? <>&#10005;</> : <> &#8801;</>}
          </OpenLinksButton>

        </LeftContainer>

        <RightContainer>
          {/* 로그인일 때 이메일 일단 표시 */}
          {ok && <button>{email}</button>}
          <NavbarLink to="/login"><button className="loginBtn">Log in</button></NavbarLink>
          <NavbarLink to="/signup"><button className="signupBtn">Sign up</button></NavbarLink>
        </RightContainer>
      </NavbarInnerContainer>
    </NavbarContainer>
  );
}

export default Navbar;
