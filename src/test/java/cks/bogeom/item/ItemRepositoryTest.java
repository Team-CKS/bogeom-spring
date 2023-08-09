package cks.bogeom.item;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemRepositoryTest {

    @Autowired
    ItemJPARepository itemJPARepository;

//    @After
//    public void cleanup() {
//        itemJPARepository.deleteAll();
//    }

    @Test
    public void 아이템저장_불러오기() {
        // given
        int id = 1;
        String name = "꼬꼬닭";
        String img = "23231";

        itemJPARepository.save(Item.builder()
                .id(id)
                .name(name)
                .img(img)
                .count(1)
                .build());

        // when
        List<Item> itemList = itemJPARepository.findAll();

        // then
        Item item = itemList.get(0);
        assertThat(item.getName()).isEqualTo(name);
        assertThat(item.getImg()).isEqualTo(img);

    }
}
