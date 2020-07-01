import { DefaultResponse } from './default.response';

export class LoginResponse extends DefaultResponse {
    token : string ;
    username : string;
    publicId: string;
}