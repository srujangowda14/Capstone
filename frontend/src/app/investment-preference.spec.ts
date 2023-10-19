import { InvestmentPreference } from './investment-preference';

describe('InvestmentPreference', () => {
  it('should create an instance', () => {
    expect(new InvestmentPreference('College fund','CONSERVATIVE','0 - 20,000','0-5 years',0)).toBeTruthy();
  });
});
