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
export const UpdatePreReview = (title: string, abstract: string, paper_direciton: string, paper_pdf: string, user_id: string) => forminstance.post("/api/UpdatePreReview", { title: title, abstract: abstract, paper_direciton: paper_direciton, paper_pdf: paper_pdf, user_id: user_id })

//http://1.15.174.76:8080/api/GetAllPreReview
export const GetAllPreReview = () => instance.get("/api/GetAllPreReview")

// http://1.15.174.76:8080/api/GetAllPersentationResults
export const GetAllPersentationResults = () => instance.get("/api/GetAllPersentationResults")

//http://1.15.174.76:8080/api/GetAllPresentationAppl
export const c = () => instance.get("/api/GetAllPresentationAppl")

//http://1.15.174.76:8080/api/GetAllApplyDegree
export const GetAllApplyDegree = () => instance.get("/api/GetAllApplyDegree")

// {
// 	"userId": "51255902041",
// 	"title": "论文题目",
// 	"keyWord": "关键字",
// 	"presentationTime": "9月1号",
// 	"directorWorkplace": "导师工作单位",
// 	"directorDirection": "导师从事学科领域",
// 	"director1Name": "导师一号",
// 	"director1Workplace": "华师大",
// 	"director1Title": "职称",
// 	"director2Name": "",
// 	"director2Workplace": "",
// 	"director2Title": "",
// 	"director3Name": "",
// 	"director3Workplace": "",
// 	"director3Title": "职称"
// }

// http://1.15.174.76:8080/api/UpdatePresentationApplication/
export const UpdatePresentationApplication = (userId: string, title: string, keyWord: string, presentationTime: string, directorWorkspace: string, director1Name: string, director1Workplace: string, director1Title: string, director2Name: string, director2Workplace: string, director2Title: string, director3Name: string, director3Workplace: string, director3Title: string,) => instance.post("/api/UpdatePresentationApplication/", { userId: userId, title: title, keyWord: keyWord, presentationTime: presentationTime, directorWorkspace: directorWorkspace, director1Name: director1Name, director1Workplace: director1Workplace, director1Title: director1Title, director2Name: director2Name, director2Workplace: director2Workplace, director2Title: director2Title, director3Name: director3Name, director3Workplace: director3Workplace, director3Title: director3Title, })


// http://1.15.174.76:8080/api/SubmitPDF
export const SubmitPDF = (formdata: FormData) => forminstance.post("/api/SubmitPDF", { formdata })


// http://1.15.174.76:8080/api/GetPresentationApplication?student_id=51255902041
export const GetPresentationApplication = (id: string) => instance.get("/api/GetPresentationApplication?student_id=" + id)

//http://1.15.174.76:8080/api/all
export const GetAll = () => instance.get("/api/all")