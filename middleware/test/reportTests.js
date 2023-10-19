const chai = require('chai');
const chaiHttp = require('chai-http');
const app = require('../app'); 
const expect = chai.expect;

chai.use(chaiHttp);

describe('report routes', ()=>{
    it('should return 200 when buy report is asked for',async ()=>{
        chai
          .request(app)
          .get('/report/buyReport/943842911')
          .end((err, res) => {
            console.log(res.body);
            expect(res).to.have.status(200); 
          });
    });

    it('should return 200 when sell report is asked for', async ()=>{
        chai
          .request(app)
          .get('/report/sellReport/943842911')
          .end((err, res) => {
            expect(res).to.have.status(200); 
            expect(res.body.length).to.be.greaterThan(0);
          });
    });
});