import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import {CommonTable ,CommonTableColumn ,CommonTableRow} from "../styles/PostListStyle"
import {postList }from '../listData';

const PostList = props => {
  const [ dataList, setDataList ] = useState([]);

  useEffect(() => {
    setDataList(postList);
  }, [ ])

  return (
    <>
      <CommonTable headersName={['글번호', '제목', '등록일', '조회수']}>
        {
          dataList ? dataList.map((item, index) => {
            return (
              <CommonTableRow key={index}>
                <CommonTableColumn>{ item.no }</CommonTableColumn>
                <CommonTableColumn>
                  <Link to={`/postView/${item.no}`}>{ item.title }</Link>
                </CommonTableColumn>
                <CommonTableColumn>{ item.createDate }</CommonTableColumn>
                <CommonTableColumn>{ item.readCount }</CommonTableColumn>
              </CommonTableRow>
            )
          }) : ''
        }
      </CommonTable>
    </>
  )
}

export default PostList;