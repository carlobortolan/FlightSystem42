///*
// * Copyright (c)  2022,  Carlo Bortolan, Fabian Fritz
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *     http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//
//package hello.world.demo;
//
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@RestController
//public class GenericEntityController {
//    private List<String> entityList = new ArrayList<>();
//
//    @RequestMapping("/entity/all")
//    public List<String> findAll() {
//        return entityList;
//    }
//
//    @RequestMapping(value = "/entity", method = RequestMethod.POST)
//    public String addEntity(String entity) {
//        entityList.add(entity);
//        return entity;
//    }
//
//    @RequestMapping("/entity/findby/{id}")
//    public String findById(@PathVariable Long id) {
//        return entityList.stream().
//                filter(entity -> entity.equals(id)).
//                findFirst().get();
//    }
//}