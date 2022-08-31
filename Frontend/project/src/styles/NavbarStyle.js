import styled from "styled-components";
import { Link } from "react-router-dom";

export const NavbarContainer = styled.nav`
  width: 100%;
  color: black;
  display: flex;
  flex-direction: column;
  @media (min-width: 700px) {
    height: 80px;
  }
  `;

export const LeftContainer = styled.div`
  flex: 80%;
  display: flex;
  align-items: center;
  padding-left: 5%;
  .inputContainer{
    background-color: aqua;
    width: 100%;
  }
  `;

export const RightContainer = styled.div`
  flex: 20%;
  display: flex;
  justify-content: flex-end;
  padding-right: 80px;
  `;
export const Logo = styled.img`
  max-width: 180px;
  height: auto;
  `;

export const NavbarInnerContainer = styled.div`
  width: 100%;
  height: 80px;
  display: flex;
  `;

export const NavbarLink = styled(Link)`
  display: flex;
  color: black;
  font-size: x-large;
  font-family: Arial, Helvetica, sans-serif;
  text-decoration: none;
  margin: 10px;
  
  img {
    /* 로고 */
    max-width: 180px;
    height: auto;
  }
  .searchForm {
    /* 검색창 */
    display: flex;
    width:100%;
    height: 40px;
   // margin-left: 40px;
    padding: 0 15px;
    cursor: pointer;
  }
  
  .loginBtn,
  .signupBtn {
    /* 로그인,회원가입 */
    cursor: pointer;
    border-radius: 5px;
    border: solid 1px rgb(1, 153, 255);
    background-color: rgb(1, 153, 255);
    font-size: 1rem;
    height: 46px;
    margin-top: 9%;
    width: 90px;
  }
  .loginBtn {
    background-color: rgb(237, 247, 254);
    color: rgb(1, 153, 255);
  }
  .signupBtn {
    font-weight: bold;
    color: #fff;
  }
  @media (max-width: 700px) {
    display: none;
  }
  `;

// 여기서 부터, 사이즈가 작아졌을때 나타나는 버거메뉴 -----
export const OpenLinksButton = styled.button`
  width: 70px;
  height: 50px;
  background: none;
  border: none;
  color: black;
  font-size: 45px;
  cursor: pointer;
  @media (min-width: 700px) {
    display: none;
  }
  `;
export const NavbarLinkExtended = styled(Link)`
  color: white;
  font-size: x-large;
  font-family: Arial, Helvetica, sans-serif;
  text-decoration: none;
  margin: 10px;
  `;

export const NavbarExtendedContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  @media (min-width: 700px) {
    display: none;
  }
`;
