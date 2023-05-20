import instance from "./request";
import forminstance from "./form_request";

// 登录接口
export const LoginApi = (id: string, password: string) =>
  instance.post("/api/login", { id: id, password: password });
//获取当前角色的借口
export const GetRole = (id: string) => instance.get("/api/GetRole?id=" + id);

//http://1.15.174.76:8080/api/getPersonalInfo/?user_id=51255902041
export const GetPersonalInfo = (id: string) => instance.get("/api/getPersonalInfo?user_id=" + id)


// 更新当前用户信息接口
export const UpdatePersonalInfo = (
  id: string,
  username: string,
  faculty: string,
  department: string,
  academicSystem: string,
  researchDirection: string,
  politicalStatus: string,
  phone: string,
  mail: string
) =>
  instance.post("/api/UpdatePersonalInfo", {
    id: id,
    username: username,
    faculty: faculty,
    department: department,
    academicSystem: academicSystem,
    researchDirection: researchDirection,
    politicalStatus: politicalStatus,
    phone: phone,
    mail: mail
  });

//获取当前
export const GetPaperTitle = (id: string) =>
  instance.get("/api/GetPaperTitle?student_id=" + id);

// http://1.15.174.76:8080/api/GetPresentationResults?student_id=51255902041

export const GetPresentationResults = (id: string) =>
  instance.get("/api/GetPresentationResults?student_id=" + id);

// 获取当前盲审结果
// http://1.15.174.76:8080/api/GetReviews?student_id=51255902041
export const GetReviews = (id: string) =>
  instance.get("/api/GetReviews?student_id=" + id);

// http://1.15.174.76:8080/api/UpdatePresentationResults4Students/
// 更新答辩结果
// {
//     "userId": "51255902041",
//     "directorNum": "5",
//     "presentationTime": "6月1日",
//     "presentationPlace": "大礼堂",
//     "votePassNum": "33",
//     "voteFailNum": "30",
//     "voteAgreeNum": "20",
//     "voteDisagreeNum": "7"
// }

export const UpdatePresentationResults = (
  userId: string,
  directorNum: string,
  presentationTime: string,
  presentationPlace: string,
  votePassNum: string,
  voteFailNum: string,
  voteAgreeNum: string,
  voteDisagreeNum: string
) =>
  instance.post("/api/UpdatePresentationResults4Students/", {
    userId: userId,
    directorNum: directorNum,
    presentationTime: presentationTime,
    presentationPlace: presentationPlace,
    votePassNum: votePassNum,
    voteFailNum: voteFailNum,
    voteAgreeNum: voteAgreeNum,
    voteDisagreeNum: voteDisagreeNum,
  });

// {
//     "id": "000",
//     "username": "000000",
//     "faculty": "",
//     "department": "",
//     "academicSystem": "",
//     "researchDirection": "",
//     "politicalStatus": "",
//     "phone": "18917304018",
//     "mail": "ttt0630@qq.com"
//  }

// 更新当前状态
// http://1.15.174.76:8080/api/UpdateDegreeApplicationStatus
export const UpdateStatus = (id: string, status: string) =>
  instance.post("/api/UpdateDegreeApplicationStatus", {
    id: id,
    degreeApplicationStatus: status,
  });

// 得到当前状态
// http://1.15.174.76:8080/api/GetStatus/?student_id=51255902041
export const GetStatus = (id: string) =>
  instance.get("/api/GetStatus/?student_id=" + id);


//http://1.15.174.76:8080/api/GetPreReview?student_id=51255902041
export const GetPreReview = (id: string) => instance.get("/api/GetPreReview?student_id=" + id)


//http://1.15.174.76:8080/api/UpdatePreReview
export const UpdatePreReview = (title: string, abstract: string, paper_direciton:string, paper_pdf:string, user_id:string) => forminstance.post("/api/UpdatePreReview", {title:title, abstract:abstract, paper_direciton:paper_direciton, paper_pdf:paper_pdf, user_id:user_id})