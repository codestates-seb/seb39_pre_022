/* eslint-disable jsx-a11y/alt-text */
import React, { useState } from "react";
import {
  NavbarContainer,
  LeftContainer,
  RightContainer,
  NavbarInnerContainer,
  NavbarLink,
  OpenLinksButton,

} from "../styles/Navbar_style";
import LogoImg from "../Img/logo.png";

function Navbar() {
  const [extendNavbar, setExtendNavbar] = useState(false);
  const [search, setSearch] = useState("");
  return (
    <NavbarContainer extendNavbar={extendNavbar}>
      <NavbarInnerContainer>
        <LeftContainer>
            <NavbarLink to="/"><img src={LogoImg}/></NavbarLink> 
            <NavbarLink to="/search">
            <input
            type="text"
            className="searchForm"
            placeholder="Search"
            onChange={e => setSearch(e.target.value)}
            />
            {console.log(search)}
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
        <NavbarLink to="/login"><button className="loginBtn">Log in</button></NavbarLink>
        <NavbarLink to="/signup"><button className="signupBtn">Sign up</button></NavbarLink>
        </RightContainer>
      </NavbarInnerContainer>
    </NavbarContainer>
  );
}

export default Navbar;