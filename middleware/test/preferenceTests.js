const chai = require('chai');
const chaiHttp = require('chai-http');
const app = require('../app'); 
const expect = chai.expect;

chai.use(chaiHttp);

describe('preference Routes', () => {
    it('should respond with a message for /preferences route', async () => {
        const res = await chai.request(app).get('/preferences');
        expect(res).to.have.status(200);
        expect(res.body).to.have.property('message').equal('Hello from /preference route!');
      });
 


  it('should return success on adding preference', async () => {
    const payload = {
        
            clientId: 109,
            investmentPurpose: "for marriage",
            riskTolerance: "above average",
            incomeCategory: "60001-80000",
            lengthOfInvestments: "7-10 years"
        
       
    }
  
      chai
        .request(app)
        .post('/preferences/prefs')
        .send(payload)
        .end((err, res) => {
          console.log(res)
          expect(res).to.have.status(200); 
          expect(res.body).to.be.an('object');
        
        });
  });


  it('should return success on updating preference', async () => {
    const payload = {
        
            clientId: 123,
            investmentPurpose: "for loan",
            riskTolerance: "above average",
            incomeCategory: "60001-80000",
            lengthOfInvestments: "7-10 years"
        
       
    }
  
      chai
        .request(app)
        .put('/preferences/prefs')
        .send(payload)
        .end((err, res) => {
          expect(res).to.have.status(200); 
          expect(res.body).to.be.an('object');
        
        });
  });


  it('should return error for invalid client id update', async () => {
    const payload = {
        clientId: 1000000,
        investmentPurpose: "for marriage",
        riskTolerance: "above average",
        incomeCategory: "60001-80000",
        lengthOfInvestments: "7-10 years"
    }
  
      chai
        .request(app)
        .put('/preferences/prefs')
        .send(payload)
        .end((err, res) => {
          expect(res).to.have.status(400); 
          expect(res.body).to.eql({
            message: 'Cannot invoke "java.util.Map.get(Object)" because "responseMap" is null',
            status: 400
          })
        
        });
  });


 

 

 

});