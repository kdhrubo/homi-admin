

package com.tryhomi.admin.autoconfigure;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import com.tryhomi.admin.domain.DomainObject;

/**
 * @author Takeshi Ogawa
 */
@Configuration
@EntityScan(basePackageClasses = DomainObject.class)
public class WallRideJpaConfiguration {

}
