package dev.mvc.notices;

import org.springframework.web.multipart.MultipartFile;

//@Getter @Setter @ToString
public class NoticesVO {

    private int noticesno;
    private String noticenumber;
    private String imageurl;
    private String receiptdate;
    private String state;
    private String publicnoticestart;
    private String publicnoticeend;
    private String species;
    private String gender;
    private String discoveryplace;
    private String characteristic;
    private String colorcd;
    private String age;
    private String weight;
    private String orgnm;
    private String careaddr;
    private String officetel;
    private String carenm;
    
    private MultipartFile imagemf;
    
    // 페이징 관련
    // -----------------------------------------------------------------------------------
    /** 시작 rownum */
    private int start_num;    
    /** 종료 rownum */
    private int end_num;    
    /** 현재 페이지 */
    private int now_page = 1;
    
    private String word="";
    
    
    public int getNoticesno() {
      return noticesno;
    }
    public void setNoticesno(int noticesno) {
      this.noticesno = noticesno;
    }
    public String getNoticenumber() {
      return noticenumber;
    }
    public void setNoticenumber(String noticenumber) {
      this.noticenumber = noticenumber;
    }
    public String getImageurl() {
      return imageurl;
    }
    public void setImageurl(String imageurl) {
      this.imageurl = imageurl;
    }
    public String getReceiptdate() {
      return receiptdate;
    }
    public void setReceiptdate(String receiptdate) {
      this.receiptdate = receiptdate;
    }
    public String getState() {
      return state;
    }
    public void setState(String state) {
      this.state = state;
    }
    public String getPublicnoticestart() {
      return publicnoticestart;
    }
    public void setPublicnoticestart(String publicnoticestart) {
      this.publicnoticestart = publicnoticestart;
    }
    public String getPublicnoticeend() {
      return publicnoticeend;
    }
    public void setPublicnoticeend(String publicnoticeend) {
      this.publicnoticeend = publicnoticeend;
    }
    public String getSpecies() {
      return species;
    }
    public void setSpecies(String species) {
      this.species = species;
    }
    public String getGender() {
      return gender;
    }
    public void setGender(String gender) {
      this.gender = gender;
    }
    public String getDiscoveryplace() {
      return discoveryplace;
    }
    public void setDiscoveryplace(String discoveryplace) {
      this.discoveryplace = discoveryplace;
    }
    public String getCharacteristic() {
      return characteristic;
    }
    public void setCharacteristic(String characteristic) {
      this.characteristic = characteristic;
    }
    public String getColorcd() {
      return colorcd;
    }
    public void setColorcd(String colorcd) {
      this.colorcd = colorcd;
    }
    public String getAge() {
      return age;
    }
    public void setAge(String age) {
      this.age = age;
    }
    public String getWeight() {
      return weight;
    }
    public void setWeight(String weight) {
      this.weight = weight;
    }
    public String getOrgnm() {
      return orgnm;
    }
    public void setOrgnm(String orgnm) {
      this.orgnm = orgnm;
    }
    public String getCareaddr() {
      return careaddr;
    }
    public void setCareaddr(String careaddr) {
      this.careaddr = careaddr;
    }
    public String getOfficetel() {
      return officetel;
    }
    public void setOfficetel(String officetel) {
      this.officetel = officetel;
    }
    public String getCarenm() {
      return carenm;
    }
    public void setCarenm(String carenm) {
      this.carenm = carenm;
    }
    public int getStart_num() {
      return start_num;
    }
    public void setStart_num(int start_num) {
      this.start_num = start_num;
    }
    public int getEnd_num() {
      return end_num;
    }
    public void setEnd_num(int end_num) {
      this.end_num = end_num;
    }
    public int getNow_page() {
      return now_page;
    }
    public void setNow_page(int now_page) {
      this.now_page = now_page;
    }
    
    
    public MultipartFile getImagemf() {
      return imagemf;
    }
    public void setImagemf(MultipartFile imagemf) {
      this.imagemf = imagemf;
    }
    
    
    
    public String getWord() {
      return word;
    }
    public void setWord(String word) {
      this.word = word;
    }
    @Override
    public String toString() {
      return "NoticesVO [noticesno=" + noticesno + ", noticenumber=" + noticenumber + ", imageurl=" + imageurl
          + ", receiptdate=" + receiptdate + ", state=" + state + ", publicnoticestart=" + publicnoticestart
          + ", publicnoticeend=" + publicnoticeend + ", species=" + species + ", gender=" + gender + ", discoveryplace="
          + discoveryplace + ", characteristic=" + characteristic + ", colorcd=" + colorcd + ", age=" + age
          + ", weight=" + weight + ", orgnm=" + orgnm + ", careaddr=" + careaddr + ", officetel=" + officetel
          + ", carenm=" + carenm + ", imagemf=" + imagemf + ", start_num=" + start_num + ", end_num=" + end_num
          + ", now_page=" + now_page + ", word=" + word + "]";
    }
    
    
}
