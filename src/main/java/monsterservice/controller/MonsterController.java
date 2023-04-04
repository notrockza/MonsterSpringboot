package monsterservice.controller;

import monsterservice.handleExceptionError.HandleExceptionError;
import monsterservice.model.Monster;
import monsterservice.service.MonsterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/monster")
public class MonsterController {

    //มันไม่รู้จักต้อง di @Autowired
    @Autowired
    private MonsterService monsterService;

    @GetMapping("/greeting")

    public String getGreeting() {
        return "Hi there!";
    }

    //@RequestBody การส่งเเบบนี้เป็น json ใน boby
    @PostMapping("/create")
    public Monster postCreate(
            @RequestBody Monster monster) {
        Monster response = monsterService.postCreateMonsterService(monster);
        return response;
    }

    @GetMapping("/get-all")
    public List<Monster> getall() {
        return monsterService.getAllMonsterService();
    }

    //RequestHeader มันจะส่งไปที่ Header
    //Header จะส่งได้หลายอย่าง
    // boby จะส่งได้อย่างเดียว

    @GetMapping("/get-information")
    public Optional<Monster> getInformation(
            @RequestHeader Integer id) {
        return monsterService.getInformation(id);
    }

    @PutMapping("/update")
    public ResponseEntity<Monster> putUpdate(
            @RequestBody Monster monster) {
        try {
            return new ResponseEntity<>(
                    monsterService.updateMonsterByIdService(monster),
                    HttpStatus.OK);

        } catch (HandleExceptionError ex) {
            return new ResponseEntity<>(new Monster(),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> delete(
            @RequestHeader Integer id) {
        try {
            return new ResponseEntity<>(
                    monsterService.deleteMonsterService(id),
                    HttpStatus.OK);
        } catch (HandleExceptionError ex) {
            return new ResponseEntity<>(
                    false,
                    HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/attack")
    public ResponseEntity<String> putAttack(
            @RequestHeader Integer id,
            @RequestHeader Integer damage) {
        try {
            return new ResponseEntity<>(
                    monsterService.attackMonsterService(id, damage),
                    HttpStatus.OK);
        } catch (HandleExceptionError ex) {
            return new ResponseEntity<>(ex.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }
    }


}
