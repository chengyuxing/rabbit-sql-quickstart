/*
 * Created by IntelliJ IDEA Rabbit SQL plugin.
 * User: chengyuxing
 * Update: 2024-11-15 22:35:01
 */

package org.example.mappers;

import com.github.chengyuxing.common.DataRow;
import com.github.chengyuxing.sql.page.IPageable;
import com.github.chengyuxing.sql.PagedResource;
import com.github.chengyuxing.sql.annotation.*;
import com.github.chengyuxing.sql.types.Param;

import java.util.List;
import java.util.Set;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

// Rabbit SQL plugin - Your imports  //CODE-BEGIN:imports

// Rabbit SQL plugin - End of your imports  //CODE-END:imports

@XQLMapper("dynamic")
public interface DynamicMapper {
    // Rabbit SQL plugin - Your methods  //CODE-BEGIN:methods

    // Rabbit SQL plugin - End of your methods  //CODE-END:methods

    /**
     * @param id id
     * @param fields fields
     * @return List[DataRow]
     */
    @XQL(type = SqlStatementType.query)
    List<DataRow> dynamicRabbit(
            @Arg("id") Object id,
            @Arg("fields") Object fields
    );

}
