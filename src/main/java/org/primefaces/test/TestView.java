package org.primefaces.test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "testView")
@ViewScoped
public class TestView implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<OuterLevelObject> firstLevelList;
	private Map<OuterLevelObject, Map<MidLevelObject, List<InnerLevelObject>>> map;

	@PostConstruct
	public void init() {
		firstLevelList = new ArrayList<OuterLevelObject>();

		OuterLevelObject outObj21 = new OuterLevelObject(21);
		OuterLevelObject outObj22 = new OuterLevelObject(22);

		firstLevelList.add(outObj21);
		firstLevelList.add(outObj22);

		MidLevelObject midObj301 = new MidLevelObject(301);
		MidLevelObject midObj302 = new MidLevelObject(302);

		InnerLevelObject innerObj71 = new InnerLevelObject(71);
		InnerLevelObject innerObj72 = new InnerLevelObject(72);
		InnerLevelObject innerObj73 = new InnerLevelObject(73);
		InnerLevelObject innerObj74 = new InnerLevelObject(74);

		map = new LinkedHashMap<OuterLevelObject, Map<MidLevelObject, List<InnerLevelObject>>>();
		map.put(outObj21, new HashMap<MidLevelObject, List<InnerLevelObject>>());
		map.get(outObj21).put(midObj301, new ArrayList<InnerLevelObject>());

		map.get(outObj21).get(midObj301).add(innerObj73);
		map.get(outObj21).get(midObj301).add(innerObj74);

		map.get(outObj21).put(midObj302, new ArrayList<InnerLevelObject>());

		map.get(outObj21).get(midObj302).add(innerObj72);

		map.put(outObj22, new HashMap<MidLevelObject, List<InnerLevelObject>>());
		map.get(outObj22).put(midObj302, new ArrayList<InnerLevelObject>());

		map.get(outObj22).get(midObj302).add(innerObj71);
	}

	public class IdWrapper {

		protected Integer id;

		public IdWrapper() {
		}

		public IdWrapper(Integer id) {
			this.id = id;
		}

		@Override
		public int hashCode() {
			int hash = 0;
			hash += (id != null ? id.hashCode() : 0);
			return hash;
		}

		@Override
		public boolean equals(Object object) {
			if (!(object instanceof IdWrapper)) {
				return false;
			}
			IdWrapper other = (IdWrapper) object;
			if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
				return false;
			}
			return true;
		}

	}

	public class OuterLevelObject extends IdWrapper {

		public OuterLevelObject(int id) {
			super(id);
		}

		@Override
		public String toString() {
			return "model.OuterLevelObject[ id=" + id + " ]";
		}

	}

	public class MidLevelObject extends IdWrapper {

		public MidLevelObject(Integer id) {
			super(id);
		}

		@Override
		public String toString() {
			return "model.MidLevelObject[ id=" + id + " ]";
		}

	}

	public class InnerLevelObject extends IdWrapper {

		public InnerLevelObject(Integer id) {
			super(id);
		}

	}

	public Set<MidLevelObject> extractMidLevelData(OuterLevelObject outObj) {
		Map<MidLevelObject, List<InnerLevelObject>> midLevelData = map.get(outObj);
		if (midLevelData != null) {
			return midLevelData.keySet();
		}
		return null;
	}

	public List<InnerLevelObject> extractInnerLevelData(OuterLevelObject outObj, MidLevelObject midObj) {
		List<InnerLevelObject> innerLevelData = map.get(outObj).get(midObj);
		return innerLevelData;
	}

	public List<OuterLevelObject> getFirstLevelList() {
		return firstLevelList;
	}

}
